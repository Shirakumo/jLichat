package org.shirakumo.lichat;
import java.util.*;

public class CL{
    private final Map<String, Package> packages;
    private final Map<String, StandardClass> classes;

    public CL(){
        packages = new HashMap<String, Package>();
        classes = new HashMap<String, StandardClass>();
        makePackage("LICHAT-PROTOCOL");
        makePackage("KEYWORD");
    }

    public Symbol makeSymbol(string name){
        return new Symbol(name);
    }

    public Symbol intern(String name, Package pkg){
        return pkg.intern(name);
    }

    public Symbol intern(String name, String pkg){
        return packages.get(pkg).intern(name);
    }

    public Symbol findSymbol(String name, Package pkg){
        return pkg.findSymbol(name);
    }

    public Symbol findSymbol(String name, String pkg){
        return packages.get(pkg).intern(name);
    }

    public Package findPackage(String name){
        return packages.get(name);
    }

    public Package makePackage(String name){
        if(packages.get(name) != null)
            throw new SimpleError("A package with the name "+name+" already exists");
        Package pkg = new Package(name);
        packages.put(name, pkg);
        return pkg;
    }

    public StandardClass defclass(String name, List<String> directSuperclasses, Map<String, Function<Object>> initforms){
        if(directSuperclasses == null){
            directSuperclasses = new ArrayList<String>();
            directSuperclasses.add("StandardObject");
        }
        List<StandardClass> classes = new ArrayList<StandardClass>();
        for(String name : directSuperclasses){
            classes.put(findClass(name));
        }
        StandardClass clas = new StandardClass(name, classes, initforms);
        this.classes.put(name, clas);
        return clas;
    }

    public StandardObject makeInstance(String name, Object... initargs){
        Map<String, Object> argmap = new HashMap<String, Object>();
        for(int i=0; i<initargs.length; i+=2){
            argmap.put((String)initargs[i], initargs[i+1]);
        }
        return new StandardObject(findClass(name), argmap);
    }

    public StandardClass findClass(String name){
        StandardClass clas = classes.get(name);
        if(clas == null) throw new SimpleError("No such class "+name+".");
        return clas;
    }

    public StandardClass classOf(StandardObject object){
        return object.clas;
    }

    public boolean typep(Object object, String type){
        if(type == null){
            return false;
        }else if(type.equal("T")){
            return true;
        }else if(type.equal("NIL")){
            return false;
        }else if(type.equal("BOOLEAN")){
            return (object == null);
        }else if(object instanceof StandardObject){
            if(classOf(object).name.equals(type)){
                return true;
            }else{
                for(StandardClass c : classOf(object).superclasses){
                    if(c.name.equals(type)){
                        return true;
                    }
                }
                return false;
            }
        }else{
            return Class.forName(type).isInstance(object);
        }
    }

    private static final long universalUnixOffset = 2208988800;

    public long getUniversalTime(){
        return Date.getTime()/1000 + universalUnixOffset;
    }

    public long universalToUnix(long universal){
        return universal - universalUnixOffset;
    }
}

class StandardClass{
    public final String name;
    public final List<StandardClass> directSuperclasses;
    public final List<StandardClass> superclasses;
    public final Map<String, Function<Object>> initforms;

    StandardClass(String name, List<StandardClass> directSuperclasses){
        this.name = name;
        this.directSuperclasses = new ArrayList<StandardClass>();
        this.directSuperclasses.addAll(directSuperclasses);
        this.superclasses = computeClassPrecedenceList();
        this.initforms = new HashMap<String, Function<Object>>();
    }

    private Function<StandardClass, Void> mapper;
    private Function<StandardClass, Void> tarjan;
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
                mapper(s);
                prev = s;
            }
        };
        mapper(this);

        tarjan = (c) -> {
            if(nodes.get(c) == 1){
                throw new SimpleError("Dependency cycle detected.");
            }
            nodes.put(c, 1);
            for(StandardClass t : edges.get(c)){
                tarjan(t);
            }
            nodes.remove(c);
            sorted.add(c);
        };
        while(true){
            Set<StandardClass> keys = nodes.keySet();
            if(keys.isEmpty()) break;
            for(StandardClass c : keys){
                tarjan(c);
                break;
            }
        }
        
        return sorted;
    }

    public StandardObject initialize(StandardObject object){
        for(String slot : initforms.keySet()){
            if(!object.slots.containsKey(slot)){
                object.s(slot, initforms.get(slot)());
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

    public StandardObject(StandardClass clas, HashMap<String, Object> initargs){
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

class Condition extends Throwable{
    public String report(){
        return "[Condition of type "+this.class.className()+"]";
    }
}

class Error extends Condition{}

class SimpleError extends Error{
    private final String message;
    
    public SimpleError(String message){
        this.message = message;
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
