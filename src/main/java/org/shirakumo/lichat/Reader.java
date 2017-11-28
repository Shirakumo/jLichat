package org.shirakumo.lichat;
import java.util.*;

public class Reader{
    public static String WHITESPACE = "\u0009\u000A\u000B\u000C\u000D\u0020\u0085\u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2008\u2009\u200A\u2028\u2029\u202F\u205F\u3000\u180E\u200B\u200C\u200D\u2060\uFEFF";
    private Stream stream;

    public Reader(Stream stream){
        this.stream = stream;
    }

    public static boolean isWhitespace(char c){
        return WHITESPACE.indexOf(c) >= 0;
    }

    private void skipWhitespace(){
        while(isWhitespace(stream.read()));
        stream.unread();
    }

    private List<Object> readSexprList(){
        
    }

    private String readSexprString(){

    }

    private Symbol readSexprKeyword(){

    }

    private double readSexprNumber(){

    }

    private Symbol readSexprSymbol(){

    }

    private Token readSexpr(){

    }

    public Token fromWire(){
        
    }
}
