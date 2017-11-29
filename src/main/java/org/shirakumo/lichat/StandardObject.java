package org.shirakumo.lichat;
import java.util.*;

public class StandardObject{
    public static final Symbol className;
    static{
        className = CL.findSymbol("STANDARD-OBJECT");
        CL.registerClass(className, StandardObject.class);
    }

    public StandardObject(Map<String, Object> initargs){
    }
}
