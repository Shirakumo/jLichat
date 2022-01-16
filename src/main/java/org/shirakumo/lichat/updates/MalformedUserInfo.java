// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class MalformedUserInfo extends UpdateFailure{
    public static final Symbol className;
    static{
        className = CL.intern("malformed-user-info", "shirakumo");
        CL.registerClass(className, MalformedUserInfo.class);
    }


    public MalformedUserInfo(Map<String, Object> initargs){
        super(initargs);
    }
}
