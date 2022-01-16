// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class ShareIdentity extends Update{
    public static final Symbol className;
    static{
        className = CL.intern("share-identity", "shirakumo");
        CL.registerClass(className, ShareIdentity.class);
    }

    public String key = null;

    public ShareIdentity(Map<String, Object> initargs){
        super(initargs);
        key = (String)CL.arg(initargs, "key");
    }
}
