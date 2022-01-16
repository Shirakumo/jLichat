// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class TargetUpdate extends Update{
    public static final Symbol className;
    static{
        className = CL.intern("target-update", "lichat");
        CL.registerClass(className, TargetUpdate.class);
    }

    public String target = null;

    public TargetUpdate(Map<String, Object> initargs){
        super(initargs);
        target = (String)CL.requiredArg(initargs, "target");
    }
}
