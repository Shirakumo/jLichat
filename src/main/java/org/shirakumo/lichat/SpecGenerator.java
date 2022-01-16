import org.shirakumo.lichat.*;
import java.io.*;
import java.util.*;
import java.util.function.Consumer;

public class SpecGenerator{
    static String toCamelCase(Symbol symbol){
        return toCamelCase(symbol.name);
    }

    static String toCamelCase(String name){
        return name;
    }
    
    class SlotDef{
        Symbol name;
        Object type;
        boolean required = true;

        SlotDef(Symbol name, Object type, boolean required){
            this.name = name;
            this.type = type;
            this.required = required;
        }

        SlotDef(List<Object> expr){
            name = (Symbol)expr.get(0);
            type = expr.get(1);
            for(int i=2; i<expr.size(); ++i){
                if(expr.get(i) == CL.intern("optional", "keyword"))
                    required = false;
            }
        }
        
        String typeName(Object type){
            if(type instanceof List){
                return String.format("List<%s>", typeName(((List<Object>)type).get(0)));
            }else if(type instanceof Symbol){
                String kind = ((Symbol)type).name;
                if(kind.equals("number"))           return "Number";
                else if(kind.equals("integer"))     return "long";
                else if(kind.equals("time"))        return "long";
                else if(kind.equals("float"))       return "float";
                else if(kind.equals("id"))          return "long";
                else if(kind.equals("symbol"))      return "Symbol";
                else if(kind.equals("keyword"))     return "Symbol";
                else if(kind.equals("boolean"))     return "boolean";
                else if(kind.equals("list"))        return "List<Object>";
                else if(kind.equals("string"))      return "String";
                else if(kind.equals("username"))    return "String";
                else if(kind.equals("channelname")) return "String";
                else if(kind.equals("password"))    return "String";
                else if(kind.equals("object"))      return "Object";
            }
            return "Object";
        }

        String defaultValue(Object type){
            if(type instanceof List){
                return String.format("new ArrayList<%s>()", typeName(((List<Object>)type).get(0)));
            }else if(type instanceof Symbol){
                String kind = ((Symbol)type).name;
                if(kind.equals("number"))           return "0";
                else if(kind.equals("integer"))     return "0";
                else if(kind.equals("time"))        return "0";
                else if(kind.equals("float"))       return "0.0";
                else if(kind.equals("id"))          return "0";
                else if(kind.equals("symbol"))      return "null";
                else if(kind.equals("keyword"))     return "null";
                else if(kind.equals("boolean"))     return "false";
                else if(kind.equals("list"))        return "new ArrayList<Object>()";
                else if(kind.equals("string"))      return "null";
                else if(kind.equals("username"))    return "null";
                else if(kind.equals("channelname")) return "null";
                else if(kind.equals("password"))    return "null";
                else if(kind.equals("object"))      return "null";
            }
            return "null";
        }
        
        String declaration(){
            return String.format("public final %s %s = %s;", typeName(type), toCamelCase(name), defaultValue(type));
        }

        String initializer(){
            String constructor = (required)? "CL.requiredArg" : "CL.arg";
            return String.format("%s = (%s)%s(initargs, \"%s\");", toCamelCase(name), typeName(type), constructor, name.name);
        }
    }
    
    class ClassDef{
        Symbol name;
        List<Symbol> superclasses = new ArrayList<Symbol>();
        Map<Symbol, SlotDef> slots = new HashMap<Symbol, SlotDef>();
        String superClass = null;

        ClassDef(List<Object> expr){
            init(expr);
        }

        void init(List<Object> expr){
            name = (Symbol)expr.get(1);
            superclasses.addAll((List<Symbol>)expr.get(2));
            for(int i=3; i<expr.size(); ++i){
                SlotDef slot = new SlotDef((List<Object>)expr.get(i));
                slots.put(slot.name, slot);
            }
        }

        void normalize(){
            if(superclasses.size() == 0){
                superClass = "Update";
            }else if(superclasses.size() == 1){
                superClass = toCamelCase(superclasses.get(0));
            }else{
                for(Symbol name : superclasses){
                    if(name.name.equals("text-update")){
                        SlotDef def = new SlotDef(CL.intern("text"), CL.intern("string"), false);
                        slots.put(def.name, def);
                    }else if(name.name.equals("target-update")){
                        SlotDef def = new SlotDef(CL.intern("target"), CL.intern("string"), false);
                        slots.put(def.name, def);
                    }else{
                        superClass = toCamelCase(name);
                    }
                }
            }
        }

        void emit(File base) throws java.io.IOException{
            normalize();
            String className = toCamelCase(name);
            File file = new File(base, className+".java");
            FileWriter writer = new FileWriter(file);
            writer.write(String.format("// File has been auto-generated."));
            writer.write(String.format("\npackage org.shirakumo.lichat.updates;"));
            writer.write(String.format("\nimport org.shirakumo.lichat.*;"));
            writer.write(String.format("\nimport java.util.*;"));
            writer.write(String.format("\n"));
            writer.write(String.format("\npublic class %s extends %s{", className, superClass));
            writer.write(String.format("\n    public static final Symbol className;"));
            writer.write(String.format("\n    static{"));
            writer.write(String.format("\n        className = CL.intern(\"%s\", \"%s\");", name.name, name.pkg));
            writer.write(String.format("\n        CL.registerClass(className, %s.class);", className));
            writer.write(String.format("\n    }"));
            for(SlotDef slot : slots.values()){
                writer.write(String.format("\n    %s", slot.declaration()));
            }
            writer.write(String.format("\n    public %s(Map<String, Object> initargs){", className));
            writer.write(String.format("\n        super(initargs);", className));
            for(SlotDef slot : slots.values()){
                writer.write(String.format("\n        %s", slot.initializer()));
            }
            writer.write(String.format("\n    }"));
            writer.write(String.format("\n}"));
            writer.close();
        }
    }

    public class Recursive<I> {
        public I func;
    }

    public void generate(File base, File ...specs) throws java.io.IOException{
        List<String> extensions = new ArrayList<String>();
        List<String> packages = new ArrayList<String>();
        Map<Symbol, ClassDef> classes = new HashMap<Symbol, ClassDef>();

        Recursive<Consumer<List<Object>>> handleDef = new Recursive<>();
        handleDef.func = expr -> {
            String deftype = ((Symbol)expr.get(0)).name;
            if(deftype.equals("define-package")){
                String name = (String)expr.get(1);
                packages.add(name);
                CL.makePackage(name);
            }else if(deftype.equals("define-object")){
                ClassDef def = new ClassDef(expr);
                classes.put(def.name, def);
            }else if(deftype.equals("define-object-extension")){
                Symbol name = (Symbol)expr.get(1);
                classes.get(name).init(expr);
            }else if(deftype.equals("define-extension")){
                extensions.add((String)expr.get(1));
                for(int i=2; i<expr.size(); ++i)
                    handleDef.func.accept((List<Object>)expr.get(i));
            }
        };
        
        for(File spec : specs){
            org.shirakumo.lichat.Reader reader = new org.shirakumo.lichat.Reader(new FileInputStream(spec));
            reader.intern = true;
            while(reader.stream.hasMore())
                handleDef.func.accept((List<Object>)reader.readSexpr());
            reader.close();
        }

        for(ClassDef cls : classes.values()){
            cls.emit(base);
        }

        File file = new File(base, "Init.java");
        FileWriter writer = new FileWriter(file);
        writer.write(String.format("// File has been auto-generated."));
        writer.write(String.format("\npackage org.shirakumo.lichat.updates;"));
        writer.write(String.format("\nimport org.shirakumo.lichat.*;"));
        writer.write(String.format("\n"));
        writer.write(String.format("\npublic class Init{"));
        // writer.write(String.format("\n    public static final List<String> EXTENSIONS = Arrays.asList(new String[]{"));
        // writer.write(String.format("\n        "));
        // for(String ext : extensions){
        //     writer.write("\"%s\", ", ext);
        // }
        // writer.write(String.format("});"));
        writer.write(String.format("\n    public static void init(){"));
        for(String pkg : packages){
            writer.write(String.format("\n        CL.makePackage(\"%s\");", pkg));
        }
        for(ClassDef cls : classes.values()){
            writer.write(String.format("\n        "));
            writer.write(String.format("\n        CL.registerClass(CL.intern(\"%s\", \"%s\"), %s.class);",
                                       cls.name.name, cls.name.pkg, toCamelCase(cls.name)));
            for(SlotDef def : cls.slots.values()){
                writer.write(String.format("\n        CL.intern(\"%s\", \"%s\");",
                                           def.name.name, def.name.pkg));
            }
        }
        writer.write(String.format("\n    }"));
        writer.write(String.format("\n}"));
        writer.close();
    }

    public static void main(String[] args) throws java.io.IOException{
        File[] files = new File[args.length-1];
        for(int i=1; i<args.length; ++i){
            files[i-1] = new File(args[i]);
        }
        new SpecGenerator().generate(new File(args[0]), files);
    }
}
