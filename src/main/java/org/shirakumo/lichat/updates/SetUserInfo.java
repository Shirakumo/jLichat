// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class SetUserInfo extends TextUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("set-user-info", "shirakumo");
        CL.registerClass(className, SetUserInfo.class);
    }

    public String key = null;

    public SetUserInfo(Map<String, Object> initargs){
        super(initargs);
        key = (String)CL.requiredArg(initargs, "key");
    }
}
