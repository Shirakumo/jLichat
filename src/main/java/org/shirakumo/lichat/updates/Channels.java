// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Channels extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("channels", "lichat");
        CL.registerClass(className, Channels.class);
    }

    public List<String> channels = new ArrayList<String>();
    public String channel = null;

    public Channels(Map<String, Object> initargs){
        super(initargs);
        channels = (List<String>)CL.arg(initargs, "channels");
        channel = (String)CL.arg(initargs, "channel");
    }
}
