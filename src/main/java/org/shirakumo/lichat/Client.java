package org.shirakumo.lichat;
import org.shirakumo.lichat.updates.*;
import org.shirakumo.lichat.conditions.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class Client extends HandlerAdapter implements Runnable{
    public static final String LICHAT_VERSION = "1.3";
    public static final int DEFAULT_PORT = 1111;
    public static final List<String> EXTENSIONS = Arrays.asList(new String[]{
            "shirakumo-data", "shirakumo-backfill", "shirakumo-emotes"});

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
    
    private List<Handler> handlers = new ArrayList<Handler>();
    private Map<Integer, List<Handler>> callbacks = new HashMap<Integer, List<Handler>>();
    private Socket socket;
    private Reader reader;
    private Printer printer;
    private Thread thread;
    private long lastReceived = 0;
    private int IdCounter = 0;
    
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
            throw new AlreadyConnected();
        thread = new Thread(this);
        thread.start();
    }

    public void disconnect(){
        if(!isConnected())
            throw new NotConnected();
        
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
            lastReceived = CL.getUniversalTime();
            try{s("DISCONNECT");}catch(Exception ex){}
            printer.close();
            printer = null;
        }
        
        if(socket != null){
            try{socket.close();}catch(IOException ex){}
            socket = null;
        }
    }

    private synchronized Object send(Object wireable){
        if(pingTimeout < (CL.getUniversalTime() - lastReceived)){
            handle(ConnectionLost.create(new PingTimeout()));
            disconnect();
        }
        printer.toWire(wireable);
        return wireable;
    }

    public StandardObject s(String className, Object... initargs){
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

        Symbol name = CL.findSymbol(className);
        if(name == null) throw new NoSuchClass(CL.makeSymbol(className));
        StandardObject update = CL.makeInstance(CL.findClass(name), argmap);
        send(update);
        return update;
    }
    
    public int nextId(){
        return IdCounter++;
    }

    public void run(){
        try{
            socket = new Socket(hostname, port);
            socket.setSoTimeout(100);
            reader = new Reader(socket.getInputStream());
            printer = new Printer(socket.getOutputStream());
            lastReceived = CL.getUniversalTime();
            s("CONNECT",
              "password", password,
              "version", LICHAT_VERSION,
              "extensions", EXTENSIONS);
            
            
            Object read = read();
            if(!(read instanceof Connect)){
                throw new InvalidUpdateReceived(read);
            }
            process((Update)read);
            while(!Thread.interrupted()){
                read = read();
                lastReceived = CL.getUniversalTime();
                if(read instanceof Update){
                    process((Update)read);
                }
            }
        }catch(IOException ex){
            process(ConnectionLost.create(ex));
        }finally{
            if(isConnected())
                disconnect();
        }
    }

    private Object read() throws IOException{
        long time = CL.getUniversalTime();
        while(!reader.stream.hasMore()){
            long passed = CL.getUniversalTime() - time;
            if(pingDelay < passed){
                s("PING");
                time = CL.getUniversalTime();
            }
        }
        return reader.fromWire();
    }

    public void process(Update update){
        int id = -1;
        if(update.id instanceof Integer) id = (Integer)update.id;
        if(update.id instanceof Long) id = (int)((long)((Long)update.id));
        if(id != -1){
            List<Handler> cbs = callbacks.get(id);
            if(cbs != null){
                for(Handler cb : cbs){
                    cb.handle(update);
                }
            }
        }
        handle(update);
        for(Handler handler : handlers){
            handler.handle(update);
        }
    }
    
    public void handle(Connect update){
        servername = update.from;
        availableExtensions.addAll(update.extensions);
        if(availableExtensions.contains("shirakumo-emotes")){
            s("EMOTES", "names", new ArrayList<String>(emotes.keySet()));
        }
    }

    public void handle(Ping update){
        s("PONG");
    }

    public void handle(Join update){
        if(update.from.equals(username) && !update.channel.equals(servername)){
            if(!channels.contains(update.channel))
                channels.add(update.channel);
            if(availableExtensions.contains("shirakumo-backfill")){
                s("BACKFILL", "channel", update.channel);
            }
        }
    }

    public void handle(Leave update){
        if(update.from.equals(username)){
            channels.remove(update.channel);
        }
    }

    public void handle(Emote update){
        emotes.put(update.name, new Payload(update));
    }

    public void addCallback(int id, Handler callback){
        if(callbacks.get(id) == null)
            callbacks.put(id, new ArrayList<Handler>());
        callbacks.get(id).add(callback);
    }

    public void removeCallback(int id){
        callbacks.remove(id);
    }

    public void addHandler(Handler handler){
        if(!handlers.contains(handler))
            handlers.add(handler);
    }

    public void removeHandler(Handler handler){
        handlers.remove(handler);
    }
}
