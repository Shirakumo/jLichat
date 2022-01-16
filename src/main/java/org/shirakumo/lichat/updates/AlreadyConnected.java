// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class AlreadyConnected extends UpdateFailure{
    public static final Symbol className;
    static{
        className = CL.intern("already-connected", "lichat");
        CL.registerClass(className, AlreadyConnected.class);
    }


    public AlreadyConnected(Map<String, Object> initargs){
        super(initargs);
    }
}
