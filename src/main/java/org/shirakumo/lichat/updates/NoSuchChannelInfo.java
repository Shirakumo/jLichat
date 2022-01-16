// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class NoSuchChannelInfo extends UpdateFailure{
    public static final Symbol className;
    static{
        className = CL.intern("no-such-channel-info", "shirakumo");
        CL.registerClass(className, NoSuchChannelInfo.class);
    }

    public Symbol key = null;

    public NoSuchChannelInfo(Map<String, Object> initargs){
        super(initargs);
        key = (Symbol)CL.requiredArg(initargs, "key");
    }
}
