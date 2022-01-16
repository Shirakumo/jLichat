// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class TooManyConnections extends Failure{
    public static final Symbol className;
    static{
        className = CL.intern("too-many-connections", "lichat");
        CL.registerClass(className, TooManyConnections.class);
    }


    public TooManyConnections(Map<String, Object> initargs){
        super(initargs);
    }
}
