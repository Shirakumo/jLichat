// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class SetChannelInfo extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("set-channel-info", "shirakumo");
        CL.registerClass(className, SetChannelInfo.class);
    }

    public Symbol key = null;
    public String text = null;

    public SetChannelInfo(Map<String, Object> initargs){
        super(initargs);
        key = (Symbol)CL.requiredArg(initargs, "key");
        text = (String)CL.arg(initargs, "text");
    }
}
