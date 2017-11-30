package org.shirakumo.lichat;
import org.shirakumo.lichat.updates.*;
import java.util.*;
import java.util.function.*;

public class CL{
    private static final Map<String, Package> packages;
    private static final Map<Symbol, Class<? extends StandardObject>> classes;
    private static final Map<Class<? extends StandardObject>, Symbol> classNames;
    public static Package PACKAGE;

    static{
        packages = new HashMap<String, Package>();
        classes = new HashMap<Symbol, Class<? extends StandardObject>>();
        classNames = new HashMap<Class<? extends StandardObject>, Symbol>();
        PACKAGE = makePackage("LICHAT-PROTOCOL");
        makePackage("KEYWORD");

        for(String name : new String[]{"ID","CLOCK","FROM","PASSWORD","VERSION","EXTENSIONS","CHANNEL","TARGET","TEXT","PERMISSIONS","USERS","CHANNELS","REGISTERED","CONNECTIONS","UPDATE-ID","COMPATIBLE-VERSIONS","CONTENT-TYPE","FILENAME","PAYLOAD","ALLOWED-CONTENT-TYPES"}){
            intern(name, "KEYWORD");
        }

        for(String name : new String[]{"NIL","T"}){
            intern(name, "LICHAT-PROTOCOL");
        }

        // Java doesn't execute the static blocks in the classes, so they
        // can't register themselves. THANKS A LOT, IDIOTS
        registerClass(intern("ALREADY-IN-CHANNEL"), AlreadyInChannel.class);
        registerClass(intern("BACKFILL"), Backfill.class);
        registerClass(intern("BAD-CONTENT-TYPE"), BadContentType.class);
        registerClass(intern("BAD-NAME"), BadName.class);
        registerClass(intern("CHANNEL-UPDATE"), ChannelUpdate.class);
        registerClass(intern("CHANNELNAME-TAKEN"), ChannelnameTaken.class);
        registerClass(intern("CHANNELS"), Channels.class);
        registerClass(intern("CONNECT"), Connect.class);
        registerClass(intern("CONNECTION-UNSTABLE"), ConnectionUnstable.class);
        registerClass(intern("CREATE"), Create.class);
        registerClass(intern("DATA"), Data.class);
        registerClass(intern("DISCONNECT"), Disconnect.class);
        registerClass(intern("EMOTE"), Emote.class);
        registerClass(intern("EMOTES"), Emotes.class);
        registerClass(intern("FAILURE"), Failure.class);
        registerClass(intern("INCOMPATIBLE-VERSION"), IncompatibleVersion.class);
        registerClass(intern("INSUFFICIENT-PERMISSIONS"), InsufficientPermissions.class);
        registerClass(intern("INVALID-PASSWORD"), InvalidPassword.class);
        registerClass(intern("INVALID-PERMISSIONS"), InvalidPermissions.class);
        registerClass(intern("INVALID-UPDATE"), InvalidUpdate.class);
        registerClass(intern("JOIN"), Join.class);
        registerClass(intern("KICK"), Kick.class);
        registerClass(intern("LEAVE"), Leave.class);
        registerClass(intern("MALFORMED-UPDATE"), MalformedUpdate.class);
        registerClass(intern("MESSAGE"), Message.class);
        registerClass(intern("NO-SUCH-CHANNEL"), NoSuchChannel.class);
        registerClass(intern("NO-SUCH-PROFILE"), NoSuchProfile.class);
        registerClass(intern("NO-SUCH-USER"), NoSuchUser.class);
        registerClass(intern("NOT-IN-CHANNEL"), NotInChannel.class);
        registerClass(intern("PERMISSIONS"), Permissions.class);
        registerClass(intern("PING"), Ping.class);
        registerClass(intern("PONG"), Pong.class);
        registerClass(intern("PULL"), Pull.class);
        registerClass(intern("REGISTER"), Register.class);
        registerClass(intern("TARGET-UPDATE"), TargetUpdate.class);
        registerClass(intern("TEXT-UPDATE"), TextUpdate.class);
        registerClass(intern("TOO-MANY-CONNECTIONS"), TooManyConnections.class);
        registerClass(intern("TOO-MANY-UPDATES"), TooManyUpdates.class);
        registerClass(intern("UPDATE"), Update.class);
        registerClass(intern("UPDATE-FAILURE"), UpdateFailure.class);
        registerClass(intern("UPDATE-TOO-LONG"), UpdateTooLong.class);
        registerClass(intern("USER-INFO"), UserInfo.class);
        registerClass(intern("USERNAME-MISMATCH"), UsernameMismatch.class);
        registerClass(intern("USERNAME-TAKEN"), UsernameTaken.class);
        registerClass(intern("USERS"), Users.class);
    }

    public static Symbol makeSymbol(String name){
        return new Symbol(null, name);
    }

    public static Symbol intern(String name){
        return intern(name, PACKAGE);
    }

    public static Symbol intern(String name, Package pkg){
        return pkg.intern(name);
    }

    public static Symbol intern(String name, String pkg){
        return packages.get(pkg).intern(name);
    }

    public static Symbol findSymbol(String name){
        return findSymbol(name, PACKAGE);
    }

    public static Symbol findSymbol(String name, Package pkg){
        return pkg.findSymbol(name);
    }

    public static Symbol findSymbol(String name, String pkg){
        return packages.get(pkg).intern(name);
    }

    public static Package findPackage(String name){
        return packages.get(name);
    }

    public static Package makePackage(String name){
        if(packages.get(name) != null)
            error("PACKAGE-ALREADY-EXISTS", "A package with the name "+name+" already exists");
        Package pkg = new Package(name);
        packages.put(name, pkg);
        return pkg;
    }

    public static StandardObject makeInstance(Symbol name, Object... initargs){
        Map<String, Object> argmap = new HashMap<String, Object>();
        for(int i=0; i<initargs.length; i+=2){
            argmap.put((String)initargs[i], initargs[i+1]);
        }
        return makeInstance(findClass(name), argmap);
    }

    public static StandardObject makeInstance(Class<? extends StandardObject> clas, Map<String, Object> initargs){
        try{
            return clas.getConstructor(Map.class).newInstance(initargs);
        }catch(NoSuchMethodException ex){
            CL.error("INVALID-CLASS-DEFINITION", "The class "+clas+" is badly defined.");
        }catch(InstantiationException ex){
            CL.error("INSTANTIATION-FAILED", "Failed to create an instance of "+clas+".");
        }catch(IllegalAccessException ex){
            CL.error("INSTANTIATION-FAILED", "Failed to create an instance of "+clas+".");
        }catch(java.lang.reflect.InvocationTargetException ex){
            if(ex.getCause() instanceof RuntimeException){
                throw (RuntimeException)ex.getCause();
            }else{
                CL.error("INSTANTIATION-FAILED", "Failed to create an instance of "+clas+".");
            }
        }
        return null;
    }

    public static Class<? extends StandardObject> registerClass(Symbol name, Class<? extends StandardObject> clas){
        classes.put(name, clas);
        classNames.put(clas, name);
        return clas;
    }

    public static Class<? extends StandardObject> findClass(Symbol name){
        Class<? extends StandardObject> clas = classes.get(name);
        if(clas == null) CL.error("NO-SUCH-CLASS", "No such class "+name+".");
        return clas;
    }

    public static Class<? extends StandardObject> classOf(StandardObject object){
        return object.getClass();
    }

    public static Symbol className(Class<? extends StandardObject> clas){
        return classNames.get(clas);
    }

    public static List<String> classSlots(Class<? extends StandardObject> clas){
        List<String> slots = new ArrayList<String>();
        for(java.lang.reflect.Field field : clas.getFields()){
            if(!java.lang.reflect.Modifier.isStatic(field.getModifiers()))
                slots.add(field.getName());
        }
        return slots;
    }

    public static Object slotValue(StandardObject object, String slot){
        try{
            return object.getClass().getField(slot).get(object);
        }catch(NoSuchFieldException ex){
            CL.error("SLOT-MISSING", "The slot "+slot+" is not present on the class "+object.className+".");
        }catch(IllegalAccessException ex){
            CL.error("SLOT-FETCH-FAILED", "Failed to fetch the slot "+slot+" from "+object+".");
        }
        return null;
    }

    public static Object requiredArg(Map<String, Object> map, String arg){
        if(!map.containsKey(arg))
            CL.error("MISSING-ARGUMENT", "The argument "+arg+" is required, but not provided.");
        return map.get(arg);
    }

    public static Condition error(String type){
        throw new Condition(type);
    }

    public static Condition error(String type, String message){
        throw new Condition(type, message);
    }

    private static final long universalUnixOffset = 2208988800L;

    public static long getUniversalTime(){
        return (new Date().getTime())/1000 + universalUnixOffset;
    }

    public static long universalToUnix(long universal){
        return universal - universalUnixOffset;
    }

    public static void sleep(float s){
        try{
            Thread.sleep((long)(s*1000));
        }catch(Exception ex){}
    }
}
