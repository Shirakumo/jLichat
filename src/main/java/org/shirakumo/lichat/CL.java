package org.shirakumo.lichat;
import java.util.*;
import java.util.function.*;

public class CL{
    private static final Map<String, Package> packages;
    private static final Map<Symbol, StandardClass> classes;
    public static Package PACKAGE;

    static{
        packages = new HashMap<String, Package>();
        classes = new HashMap<Symbol, StandardClass>();
        PACKAGE = makePackage("LICHAT-PROTOCOL");
        makePackage("KEYWORD");
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

    public static StandardClass defclass(Symbol name, List<Symbol> directSuperclasses, Map<String, Supplier<Object>> initforms){
        if(directSuperclasses == null){
            directSuperclasses = new ArrayList<Symbol>();
            directSuperclasses.add(findSymbol("STANDARD-OBJECT"));
        }
        List<StandardClass> classes = new ArrayList<StandardClass>();
        for(Symbol s : directSuperclasses){
            classes.add(findClass(s));
        }
        StandardClass clas = new StandardClass(name, classes, initforms);
        CL.classes.put(name, clas);
        return clas;
    }

    public static StandardObject makeInstance(Symbol name, Object... initargs){
        Map<String, Object> argmap = new HashMap<String, Object>();
        for(int i=0; i<initargs.length; i+=2){
            argmap.put((String)initargs[i], initargs[i+1]);
        }
        return new StandardObject(findClass(name), argmap);
    }

    public static StandardClass findClass(Symbol name){
        StandardClass clas = classes.get(name);
        if(clas == null) CL.error("NO-SUCH-CLASS", "No such class "+name+".");
        return clas;
    }

    public static StandardClass classOf(StandardObject object){
        return object.clas;
    }

    public static boolean typep(Object object, String type){
        if(type == null){
            return false;
        }else if(type.equals("T")){
            return true;
        }else if(type.equals("NIL")){
            return false;
        }else if(type.equals("BOOLEAN")){
            return (object == null);
        }else if(object instanceof StandardObject){
            if(classOf((StandardObject)object).name.equals(type)){
                return true;
            }else{
                for(StandardClass c : classOf((StandardObject)object).superclasses){
                    if(c.name.equals(type)){
                        return true;
                    }
                }
                return false;
            }
        }else{
            try{
                return Class.forName(type).isInstance(object);
            }catch(ClassNotFoundException ex){
                return false;
            }
        }
    }

    public static void error(String type){
        throw new Condition(type);
    }

    public static void error(String type, String message){
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

class StandardClass{
    public final Symbol name;
    public final List<StandardClass> directSuperclasses;
    public final List<StandardClass> superclasses;
    public final Map<String, Supplier<Object>> initforms;

    StandardClass(Symbol name, List<StandardClass> directSuperclasses, Map<String, Supplier<Object>> initforms){
        this.name = name;
        this.directSuperclasses = new ArrayList<StandardClass>();
        this.directSuperclasses.addAll(directSuperclasses);
        this.superclasses = computeClassPrecedenceList();
        this.initforms = new HashMap<String, Supplier<Object>>();
        this.initforms.putAll(initforms);
    }

    private Consumer<StandardClass> mapper;
    private Consumer<StandardClass> tarjan;
    private List<StandardClass> computeClassPrecedenceList(){
        Map<StandardClass, Integer> nodes = new HashMap<StandardClass, Integer>();
        Map<StandardClass, List<StandardClass>> edges = new HashMap<StandardClass, List<StandardClass>>();
        List<StandardClass> sorted = new ArrayList<StandardClass>();

        mapper = (c) -> {
            nodes.put(c, 0);
            StandardClass prev = c;
            for(StandardClass s : c.directSuperclasses){
                if(edges.get(prev) == null) edges.put(prev, new ArrayList<StandardClass>());
                if(!edges.get(prev).contains(s)) edges.get(prev).add(s);
                mapper.accept(s);
                prev = s;
            }
        };
        mapper.accept(this);

        tarjan = (c) -> {
            if(nodes.get(c) == 1){
                CL.error("DEPENDENCY-CYCLE", "The superclasses have a cycle.");
            }
            nodes.put(c, 1);
            for(StandardClass t : edges.get(c)){
                tarjan.accept(t);
            }
            nodes.remove(c);
            sorted.add(c);
        };
        while(true){
            Set<StandardClass> keys = nodes.keySet();
            if(keys.isEmpty()) break;
            for(StandardClass c : keys){
                tarjan.accept(c);
                break;
            }
        }
        
        return sorted;
    }

    public StandardObject initialize(StandardObject object){
        for(String slot : initforms.keySet()){
            if(!object.slots.containsKey(slot)){
                object.s(slot, initforms.get(slot).get());
            }
        }
        for(StandardClass s : directSuperclasses){
            s.initialize(object);
        }
        return object;
    }
}

class StandardObject{
    public final StandardClass clas;
    public final Map<String, Object> slots;

    public StandardObject(StandardClass clas, Map<String, Object> initargs){
        this.clas = clas;
        this.slots = new HashMap<String, Object>();
        slots.putAll(initargs);
        clas.initialize(this);
    }

    public Object g(String slot){
        return slots.get(slot);
    }

    public Object s(String slot, Object object){
        slots.put(slot, object);
        return object;
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

class Symbol{
    public final String name;
    public final Package pkg;

    Symbol(Package pkg, String name){
        this.pkg = pkg;
        this.name = name;
    }
}

class Package{
    public final String name;
    private final Map<String, Symbol> symbols;
    
    Package(String name){
        this.name = name;
        symbols = new HashMap<String, Symbol>();
    }

    public Symbol findSymbol(String name){
        return symbols.get(name);
    }

    public Symbol intern(String name){
        Symbol symbol = symbols.get(name);
        if(symbol == null){
            symbol = new Symbol(this, name);
            symbols.put(name, symbol);
        }
        return symbol;
    }
}
