// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Ping extends Update{
    public static final Symbol className;
    static{
        className = CL.intern("ping", "lichat");
        CL.registerClass(className, Ping.class);
    }


    public Ping(Map<String, Object> initargs){
        super(initargs);
    }
}
