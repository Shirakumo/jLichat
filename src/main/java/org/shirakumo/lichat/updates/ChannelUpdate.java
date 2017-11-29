package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class ChannelUpdate extends Update{
    public static final Symbol className;
    static{
        className = CL.findSymbol("CHANNEL-UPDATE");
        CL.registerClass(className, ChannelUpdate.class);
    }

    public final String channel = null;

    public ChannelUpdate(Map<String, Object> initargs){
        super(initargs);
        
    }
}
