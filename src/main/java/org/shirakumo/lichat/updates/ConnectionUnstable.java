// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class ConnectionUnstable extends Failure{
    public static final Symbol className;
    static{
        className = CL.intern("connection-unstable", "lichat");
        CL.registerClass(className, ConnectionUnstable.class);
    }


    public ConnectionUnstable(Map<String, Object> initargs){
        super(initargs);
    }
}
