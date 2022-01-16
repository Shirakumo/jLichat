package org.shirakumo.lichat;
import org.shirakumo.lichat.updates.*;
import org.shirakumo.lichat.conditions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

public class CL{
    private static final Map<String, Package> packages = new HashMap<String, Package>();
    private static final Map<Symbol, Class<? extends StandardObject>> classes = new HashMap<Symbol, Class<? extends StandardObject>>();
    private static final Map<Class<? extends StandardObject>, Symbol> classNames = new HashMap<Class<? extends StandardObject>, Symbol>();
    public static Package PACKAGE;

    static{
        PACKAGE = makePackage("lichat");
        makePackage("keyword");

        for(String name : new String[]{"NIL","T", "AND", "OR", "NOT", "+", "-"}){
            intern(name);
        }

        Init.init();
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
        return findPackage(pkg).intern(name);
    }

    public static Symbol findSymbol(String name){
        return findSymbol(name, PACKAGE);
    }

    public static Symbol findSymbol(String name, Package pkg){
        return pkg.findSymbol(name);
    }

    public static Symbol findSymbol(String name, String pkg){
        return findPackage(pkg).intern(name);
    }

    public static Package findPackage(String name){
        return packages.get(name.toLowerCase());
    }

    public static Package makePackage(String name){
        name = name.toLowerCase();
        if(packages.get(name) != null)
            throw new PackageAlreadyExists(name);
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
            throw new InvalidClassDefinition(clas);
        }catch(InstantiationException ex){
            throw new InstantiationFailed(clas, initargs);
        }catch(IllegalAccessException ex){
            throw new InstantiationFailed(clas, initargs);
        }catch(java.lang.reflect.InvocationTargetException ex){
            if(ex.getCause() instanceof RuntimeException){
                throw (RuntimeException)ex.getCause();
            }else{
                throw new InstantiationFailed(clas, initargs);
            }
        }
    }

    public static Class<? extends StandardObject> registerClass(Symbol name, Class<? extends StandardObject> clas){
        classes.put(name, clas);
        classNames.put(clas, name);
        return clas;
    }

    public static Class<? extends StandardObject> findClass(Symbol name){
        Class<? extends StandardObject> clas = classes.get(name);
        if(clas == null) throw new NoSuchClass(name);
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
            throw new SlotMissing(object, slot);
        }catch(IllegalAccessException ex){
            throw new SlotValueFailed(object, slot);
        }
    }

    public static Object requiredArg(Map<String, Object> map, String arg){
        if(!map.containsKey(arg))
            throw new MissingArgument(arg);
        return map.get(arg);
    }

    public static Object arg(Map<String, Object> map, String arg){
        return arg(map, arg, null);
    }

    public static Object arg(Map<String, Object> map, String arg, Object def){
        if(!map.containsKey(arg))
            return def;
        else{
            Object value = map.get(arg);
            if(value == findSymbol("NIL"))
                return null;
            else
                return value;
        }
    }

    public static Condition error(String message){
        throw new Condition(message);
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

    public static void sleep(double s){
        try{
            Thread.sleep((long)(s*1000));
        }catch(Exception ex){}
    }

    public static byte[] readOctetStream(java.io.InputStream in) throws IOException{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int nRead;
        byte[] buffer = new byte[4096];
        while ((nRead = in.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, nRead);
        }
        in.close();
        return out.toByteArray();
    }
}
