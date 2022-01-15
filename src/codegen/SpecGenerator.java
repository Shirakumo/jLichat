import org.shirakumo.lichat.*;
import java.io.*;
import java.util.*;

public class SpecGenerator{
    class SlotDef{
        Symbol name;
        Object type;
        boolean required = true;

        SlotDef(List<Object> expr){
            name = (Symbol)expr.get(0);
            type = expr.get(1);
            for(int i=2; i<expr.size(); ++i){
                if(expr.get(i) == CL.intern("optional", "keyword"))
                    required = false;
            }
        }
    }
    
    class ClassDef{
        Symbol name;
        List<Symbol> superclasses = new ArrayList<Symbol>();
        Map<Symbol, SlotDef> slots = new HashMap<Symbol, SlotDef>();

        ClassDef(List<Object> expr){
            init(expr);
        }

        init(List<Object> expr){
            name = expr.get(1);
            superclasses.addAll((List<Symbol>)expr.get(2));
            for(int i=3; i<expr.size(); ++i){
                SlotDef slot = new SlotDef((List<Object>)expr.get(i));
                slots.put(slot);
            }
        }

        emit(File base){
            String className = toCamelCase(name);
            File file = new File(base, className+".java");
            FileWriter writer = new FileWriter(file);
            writer.write(String.format("
// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class %s extends %s{
    public static final Symbol className;
    static{
        className = CL.intern(\"%s\", \"%s\");
        CL.registerClass(className, %1$s.class);
    }\n", className, superClass, name.name, name.package));
            for(SlotDef slot : map.values()){
                String name = toCamelCase(slot.name);
                String type = "Object";
                String value = "null";
                writer.write(String.format("   public final %s %s = %s;\n", type, name, value));
            }
            writer.write(String.format("   public %s(Map<String, Object> initargs){", className));
            writer.write(String.format("      super(initargs);", className));
            for(SlotDef slot : map.values()){
                String name = toCamelCase(slot.name);
                String type = "Object";
                String constructor = (slot.optional)? "CL.arg" : "CL.requiredArg";
                String symname = slot.name.name;
                writer.write(String.format("      %s = (%s)%s(initargs, \"%s\");\n", name, type, constructor, symname));
            }
            writer.write(String.format("  }\n}\n"));
        }
    }

    
    public static void main(String[] args){
        String target = args[0];

        List<String> extensions = new ArrayList<String>();
        List<String> packages = new ArrayList<String>();
        Map<Symbol, ClassDef> classes = new HashMap<String, ClassDef>();

        Consumer<List<Object>> handleDef;
        handleDef = expr -> {
            Symbol deftype = ((Symbol)expr.get(0)).name;
            if(deftype.equals("define-package")){
                String name = (String)expr.get(1);
                packages.add(name);
                CL.makePackage(name);
            }else if(deftype.equals("define-object")){
                Symbol name = (Symbol)expr.get(1);
                ClassDef def = new ClassDef(expr);
            }else if(deftype.equals("define-object-extension")){
                Symbol name = (Symbol)expr.get(1);
                classes.get(name).init(expr);
            }else if(deftype.equals("define-extension")){
                extensions.add((String)expr.get(1));
                for(int i=2; i<expr.size(); ++i)
                    handleDef((List<Object>)expr.get(i));
            }
        };
        
        for(int i=1; i<args.length; ++i){
            Reader reader = new Reader(new FileInputStream(new File(args[i])));
            reader.intern = true;
            while(reader.stream.hasMore())
                handleDef((List<Object>)reader.readSexpr());
            reader.close();
        }
    }
}
