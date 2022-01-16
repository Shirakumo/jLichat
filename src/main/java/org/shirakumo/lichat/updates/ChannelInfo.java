// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class ChannelInfo extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("channel-info", "shirakumo");
        CL.registerClass(className, ChannelInfo.class);
    }

    public List<List<Symbol>> keys = new ArrayList<List<Symbol>>();

    public ChannelInfo(Map<String, Object> initargs){
        super(initargs);
        keys = (List<List<Symbol>>)CL.requiredArg(initargs, "keys");
    }
}
