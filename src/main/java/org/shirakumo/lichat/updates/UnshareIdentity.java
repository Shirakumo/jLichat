// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class UnshareIdentity extends Update{
    public static final Symbol className;
    static{
        className = CL.intern("unshare-identity", "shirakumo");
        CL.registerClass(className, UnshareIdentity.class);
    }

    public String key = null;

    public UnshareIdentity(Map<String, Object> initargs){
        super(initargs);
        key = (String)CL.arg(initargs, "key");
    }
}
