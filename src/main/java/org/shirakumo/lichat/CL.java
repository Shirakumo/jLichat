package org.shirakumo.lichat;
import java.util.*;
import java.util.function.*;

public class CL{
    private static final Map<String, Package> packages;
    private static final Map<Symbol, Class<? extends StandardObject>> classes;
    public static Package PACKAGE;

    static{
        packages = new HashMap<String, Package>();
        classes = new HashMap<Symbol, Class<? extends StandardObject>>();
        PACKAGE = makePackage("LICHAT-PROTOCOL");
        makePackage("KEYWORD");

        for(String name : new String[]{"ID","CLOCK","FROM","PASSWORD","VERSION","EXTENSIONS","CHANNEL","TARGET","TEXT","PERMISSIONS","USERS","CHANNELS","REGISTERED","CONNECTIONS","UPDATE-ID","COMPATIBLE-VERSIONS","CONTENT-TYPE","FILENAME","PAYLOAD","ALLOWED-CONTENT-TYPES"}){
            intern(name, "KEYWORD");
        }

        for(String name : new String[]{"NIL","T"}){
            intern(name, "LICHAT-PROTOCOL");
        }
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
        try{
            return findClass(name).getConstructor(Map.class).newInstance(argmap);
        }catch(NoSuchMethodException ex){
            CL.error("INVALID-CLASS-DEFINITION", "The class "+name+" is badly defined.");
        }catch(InstantiationException ex){
            CL.error("INSTANTIATION-FAILED", "Failed to create an instance of "+name+".");
        }catch(IllegalAccessException ex){
            CL.error("INSTANTIATION-FAILED", "Failed to create an instance of "+name+".");
        }catch(java.lang.reflect.InvocationTargetException ex){
            if(ex.getCause() instanceof RuntimeException){
                throw (RuntimeException)ex.getCause();
            }else{
                CL.error("INSTANTIATION-FAILED", "Failed to create an instance of "+name+".");
            }
        }
        return null;
    }

    public static Class<? extends StandardObject> registerClass(Symbol name, Class<? extends StandardObject> clas){
        classes.put(name, clas);
        return clas;
    }

    public static Class<? extends StandardObject> findClass(Symbol name){
        Class<? extends StandardObject> clas = classes.get(name);
        if(clas == null) CL.error("NO-SUCH-CLASS", "No such class "+name+".");
        return clas;
    }

    public static Class<? extends StandardObject> classOf(StandardObject object){
        return findClass(object.className);
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
            return findClass(object.className).getField(slot).get(object);
        }catch(NoSuchFieldException ex){
            CL.error("SLOT-MISSING", "The slot "+slot+" is not present on the class "+object.className+".");
        }catch(IllegalAccessException ex){
            CL.error("SLOT-FETCH-FAILED", "Failed to fetch the slot "+slot+" from "+object+".");
        }
        return null;
    }

    public static boolean typep(Object object, String type){
        if(type == null){
            return false;
        }else if(type.equals("T")){
            return true;
        }else if(type.equals("NIL")){
            return false;
        }else if(type.equals("NULL")){
            return (object == null);
        }else if(type.equals("BOOLEAN")){
            return (object == null);
        }else{
            try{
                return Class.forName(type).isInstance(object);
            }catch(ClassNotFoundException ex){
                return false;
            }
        }
    }

    public static Object requiredArg(Map<String, Object> map, String arg){
        Object result = map.get(arg);
        if(result == null)
            CL.error("MISSING-ARGUMENT", "The argument "+arg+" is required, but not provided.");
        return result;
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
}

class Condition extends RuntimeException{
    private final String message;
    private final String type;

    public Condition(String type){
        this.type = type;
        this.message = this.toString();
    }
    
    public Condition(String type, String message){
        this.type = type;
        this.message = message;
    }

    public String toString(){
        return "[Condition of type "+getClass().getName()+"]";
    }

    public String report(){
        return message;
    }
}
