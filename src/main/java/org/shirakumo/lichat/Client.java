package org.shirakumo.lichat;
import org.shirakumo.lichat.updates.*;
import org.shirakumo.lichat.conditions.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Client extends HandlerAdapter implements Runnable{
    public static final String LICHAT_VERSION = "2.0";
    public static final int DEFAULT_PORT = 1111;
    public static final List<String> EXTENSIONS = Arrays.asList(new String[]{
            "shirakumo-data", "shirakumo-backfill", "shirakumo-emotes", "shirakumo-channel-info",
            "shirakumo-edit", "shirakumo-quiet", "shirakumo-pause", "shirakumo-ip",
            "shirakumo-server-management", "shirakumo-channel-trees"});

    public int pingDelay = 10;
    public int pingTimeout = 60;
    public String servername = null;
    public String username = "Lion";
    public String password = null;
    public String hostname = "localhost";
    public int port = DEFAULT_PORT;
    public final List<String> channels = new ArrayList<String>();
    public final List<String> availableExtensions = new ArrayList<String>();
    public final Map<String,Payload> emotes = new HashMap<String,Payload>();
    
    private final List<Handler> handlers = new java.util.concurrent.CopyOnWriteArrayList<Handler>();
    private final Map<Long, List<Handler>> callbacks = new HashMap<Long, List<Handler>>();
    private final Queue<Object> sendQueue = new ConcurrentLinkedQueue<Object>();
    private Socket socket;
    private Reader reader;
    private Printer printer;
    private Thread thread;
    private long lastReceived = 0;
    private long IdCounter = 0;
    
    public Client(){
    }

    public Client(String username, String password, String hostname, int port){
        this.username = username;
        this.password = password;
        this.hostname = hostname;
        this.port = port;
    }

    public boolean isConnected(){
        return socket != null && socket.isConnected() && !socket.isClosed();
    }

    public void connect(){
        if(isConnected())
            throw new org.shirakumo.lichat.conditions.AlreadyConnected();
        thread = new Thread(this);
        thread.start();
    }

    public void disconnect(){
        if(!isConnected())
            throw new NotConnected();

        lastReceived = CL.getUniversalTime();
        try{s("DISCONNECT");}catch(Exception ex){}
    }

    private void cleanup(){
        while(sendQueue.poll() != null);
        channels.clear();
        availableExtensions.clear();
        servername = null;

        if(thread != null){
            thread.interrupt();
            try{thread.join(1000);}catch(Exception ex){}
            thread = null;
        }

        if(reader != null){
            reader.close();
            reader = null;
        }

        if(printer != null){
            printer.close();
            printer = null;
        }

        if(socket != null){
            try{socket.close();}catch(IOException ex){}
            socket = null;
        }
    }

    private synchronized Object send(Object wireable){
        sendQueue.offer(wireable);
        return wireable;
    }

    public Update s(String className, Object... initargs){
        Update update = construct(className, initargs);
        send(update);
        return update;
    }

    public Update construct(String className, Object... initargs){
        Symbol name = CL.findSymbol(className);
        if(name == null) throw new NoSuchClass(CL.makeSymbol(className));
        return construct(name, initargs);
    }

    public Update construct(Symbol className, Object... initargs){
        return construct(CL.findClass(className), initargs);
    }

    public Update construct(Class<? extends StandardObject> cls, Object... initargs){
        Map<String, Object> argmap = new HashMap<String, Object>();
        for(int i=0; i<initargs.length; i+=2){
            argmap.put((String)initargs[i], initargs[i+1]);
        }
        if(!argmap.containsKey("clock"))
            argmap.put("clock", CL.getUniversalTime());
        if(!argmap.containsKey("id"))
            argmap.put("id", nextId());
        if(!argmap.containsKey("from"))
            argmap.put("from", username);

        return (Update)CL.makeInstance(cls, argmap);
    }
    
    public long nextId(){
        return IdCounter++;
    }

    public void run(){
        try{
            socket = new Socket(hostname, port);
            socket.setSoTimeout(100);
            reader = new Reader(socket.getInputStream());
            printer = new Printer(socket.getOutputStream());
            lastReceived = CL.getUniversalTime();

            printer.toWire(CL.makeInstance(CL.findSymbol("connect"),
                                           "clock", CL.getUniversalTime(),
                                           "id", nextId(),
                                           "from", username,
                                           "password", password,
                                           "version", LICHAT_VERSION,
                                           "extensions", EXTENSIONS));
            CL.sleep(0.1);
            Object read = reader.fromWire();
            if(!(read instanceof Connect)){
                throw new InvalidUpdateReceived(read);
            }
            Update update = (Update) read;
            if(username == null || username.equals(""))
                username = update.from;
            process(update);

            long lastPing = CL.getUniversalTime();
            while(!Thread.interrupted()){
                for(Object o = sendQueue.poll(); o != null; o = sendQueue.poll()){
                    printer.toWire(o);
                }
                if(reader.stream.hasMore()){
                    read = reader.fromWire();
                }
                if(read instanceof Update){
                    update = (Update) read;
                    lastReceived = CL.getUniversalTime();
                    lastPing = lastReceived;
                    if(update.from == null) update.from = username;
                    process(update);
                    read = null;
                }
                if(pingDelay < (CL.getUniversalTime() - lastPing)){
                    lastPing = CL.getUniversalTime();
                    s("ping");
                }
                if(pingTimeout < (CL.getUniversalTime() - lastReceived)){
                    throw new PingTimeout();
                }
            }
        }catch(Exception ex){
            process(ConnectionLost.create(ex));
        }finally{
            if(isConnected())
                printer.toWire(construct("disconnect"));
            cleanup();
        }
    }

    // FIXME: Some kind of way to notify of exceptions that we just ignore in here.
    public void process(Update update){
        long id = update.id;
        if(id != -1){
            List<Handler> cbs = callbacks.get(id);
            if(cbs != null){
                for(Handler cb : cbs){
                    try{cb.handle(update);}catch(Exception ex){}
                }
            }
        }
        try{handle(update);}catch(Exception ex){}
        for(Handler handler : handlers){
            try{handler.handle(update);}catch(Exception ex){}
        }
    }
    
    public void handle(Connect update){
        servername = update.from;
        availableExtensions.addAll(update.extensions);
        if(availableExtensions.contains("shirakumo-emotes")){
            s("emotes", "names", new ArrayList<String>(emotes.keySet()));
        }
    }

    public void handle(Ping update){
        s("pong");
    }

    public void handle(Join update){
        if(update.from.equals(username) && !update.channel.equals(servername)){
            if(!channels.contains(update.channel))
                channels.add(update.channel);
            if(availableExtensions.contains("shirakumo-backfill"))
                s("backfill", "channel", update.channel);
            if(availableExtensions.contains("shirakumo-channel-info"))
                s("channel-info", "channel", update.channel, "keys", CL.intern("T"));
        }
    }

    public void handle(Leave update){
        if(update.from.equals(username)){
            channels.remove(update.channel);
        }
    }

    public void handle(Emote update){
        emotes.put(update.name.toLowerCase(), new Payload(update));
    }

    public void addCallback(long id, Handler callback){
        if(callbacks.get(id) == null)
            callbacks.put(id, new ArrayList<Handler>());
        callbacks.get(id).add(callback);
    }

    public void removeCallback(long id){
        callbacks.remove(id);
    }

    public void addHandler(Handler handler){
        if(!handlers.contains(handler))
            handlers.add(handler);
    }

    public void removeHandler(Handler handler){
        handlers.remove(handler);
    }

    public Handler[] listHandlers(){
        return handlers.toArray(new Handler[0]);
    }
}
