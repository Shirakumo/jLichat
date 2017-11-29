package org.shirakumo.lichat;
import org.shirakumo.lichat.updates.*;
import java.util.*;

public class Client{
    public static final String LICHAT_VERSION = "1.3";
    public static final int DEFAULT_PORT = 1111;
    
    public String username = "Lion";
    public String password = null;
    public String hostname = "localhost";
    public int port = DEFAULT_PORT;

    private List<Handler> handlers = new ArrayList<Handler>();
    private Map<Integer, List<Handler>> callbacks = new HashMap<Integer, List<Handler>>();
    
    public Client(){
    }

    public Client(String username, String password, String hostname, int port){
        this.username = username;
        this.password = password;
        this.hostname = hostname;
        this.port = port;
    }

    public void connect(){

    }

    public void disconnect(){
        
    }

    private Object send(Object wireable){
        return wireable;
    }

    public Object s(String className, Object... initargs){
        return null;
    }

    public void handle(){
        
    }

    public void process(Update update){
        List<Handler> cbs = callbacks.get((Integer)update.id);
        if(cbs != null){
            for(Handler cb : cbs){
                cb.handle(update);
            }
        }
        for(Handler handler : handlers){
            handler.handle(update);
        }
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
