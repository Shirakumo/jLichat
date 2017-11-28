package org.shirakumo.lichat;
import java.util.*;

public class Printer{
    private final OutputStream stream;

    public Printer(OutputStream stream){
        this.stream = stream;
    }

    private void printSexprList(List<Object> list){
        stream.write("(");
        try{
            for(int i=0; i<list.size(); i++){
                printSexpr(list.get(i));
                if(i+1 < list.size())
                    stream.write(" ");
            }
        }finally{
            stream.write(")");
        }
    }

    private void printSexprString(String string){
        stream.write("\"");
        try{
            for(int cp : (Iterable<Integer>) () -> string.codePoints().iterator()){
                if(cp == 92 /* \ */ || cp == 34 /* " */){
                    stream.write("\\");
                }
                stream.write(cp);
            }
        }finally{
            stream.write("\"");
        }
    }

    private void printSexprNumber(double number){
        stream.write(new java.math.BigDecimal(number).stripTrailingZeros().toPlainString());
    }

    private void printSexprToken(String token){
        for(int cp : (Iterable<Integer>) () -> token.codePoints().iterator()){
            switch(cp){
            case 92: /* \ */
            case 35: /* # */
            case 40: /* ( */
            case 41: /* ) */
            case 32: /*   */
            case 34: /* " */
            case 58: /* : */
            case 46: /* . */
            case 48: /* 0 */
            case 49: /* 1 */
            case 50: /* 2 */
            case 51: /* 3 */
            case 52: /* 4 */
            case 53: /* 5 */
            case 54: /* 6 */
            case 55: /* 7 */
            case 56: /* 8 */
            case 57: /* 9 */
                stream.write("\\");
                break;
            }
            stream.write(cp);
        }
    }

    private void printSexprSymbol(Symbol symbol){
        if(symbol.pkg == null){
            stream.write("#:");
        }else if(symbol.pkg == CL.findPackage("KEYWORD")){
            stream.write(":");
        }else if(symbol.pkg == CL.findPackage("LICHAT-PROTOCOL")){
        }else{
            printSexprToken(symbol.pkg.name);
            stream.write(":");
        }
        printSexprToken(symbol.name);
    }

    private void printSexpr(Object sexpr){
        if(sexpr == null){
            printSexprToken("NIL");
        }else if(sexpr instanceof String){
            printSexprString((String)sexpr);
        }else if(sexpr instanceof List){
            printSexprList((List<Object>)sexpr);
        }else if(sexpr instanceof Double){
            printSexprNumber((Double)sexpr);
        }else if(sexpr instanceof Symbol){
            printSexprSymbol((Symbol)sexpr);
        }else{
            CL.error("UNPRINTABLE-OBJECT", "The object "+sexpr+" cannot be printed.");
        }
    }

    public void toWire(Object object){
        if(CL.typep(object, "WIRE-OBJECT")){
            StandardObject wireable = (StandardObject)object;
            List<Object> list = new ArrayList<Object>();
            list.add(wireable.clas.name);
            for(String slot : wireable.slots.keySet()){
                list.add(CL.findSymbol(slot.toUpperCase(), "KEYWORD"));
                list.add(wireable.g(slot));
            }
            printSexpr(list);
        }else{
            printSexpr(object);
        }
    }
}
