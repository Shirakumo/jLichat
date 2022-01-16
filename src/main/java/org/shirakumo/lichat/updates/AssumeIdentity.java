// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class AssumeIdentity extends TargetUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("assume-identity", "shirakumo");
        CL.registerClass(className, AssumeIdentity.class);
    }

    public String key = null;

    public AssumeIdentity(Map<String, Object> initargs){
        super(initargs);
        key = (String)CL.requiredArg(initargs, "key");
    }
}
