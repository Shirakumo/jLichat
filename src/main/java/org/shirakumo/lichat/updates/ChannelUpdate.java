// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class ChannelUpdate extends Update{
    public static final Symbol className;
    static{
        className = CL.intern("channel-update", "lichat");
        CL.registerClass(className, ChannelUpdate.class);
    }

    public String channel = null;
    public String bridge = null;

    public ChannelUpdate(Map<String, Object> initargs){
        super(initargs);
        channel = (String)CL.requiredArg(initargs, "channel");
        bridge = (String)CL.arg(initargs, "bridge");
    }
}
