// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Emote extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("emote", "shirakumo");
        CL.registerClass(className, Emote.class);
    }

    public String contentType = null;
    public String payload = null;
    public String name = null;

    public Emote(Map<String, Object> initargs){
        super(initargs);
        contentType = (String)CL.requiredArg(initargs, "content-type");
        payload = (String)CL.requiredArg(initargs, "payload");
        name = (String)CL.requiredArg(initargs, "name");
    }
}
