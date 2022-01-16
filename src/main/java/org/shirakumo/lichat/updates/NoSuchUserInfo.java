// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class NoSuchUserInfo extends UpdateFailure{
    public static final Symbol className;
    static{
        className = CL.intern("no-such-user-info", "shirakumo");
        CL.registerClass(className, NoSuchUserInfo.class);
    }

    public Symbol key = null;

    public NoSuchUserInfo(Map<String, Object> initargs){
        super(initargs);
        key = (Symbol)CL.requiredArg(initargs, "key");
    }
}
