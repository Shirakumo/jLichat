// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class React extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("react", "shirakumo");
        CL.registerClass(className, React.class);
    }

    public String target = null;
    public long updateId = 0;
    public String emote = null;

    public React(Map<String, Object> initargs){
        super(initargs);
        target = (String)CL.requiredArg(initargs, "target");
        updateId = (long)CL.requiredArg(initargs, "update-id");
        emote = (String)CL.requiredArg(initargs, "emote");
    }
}
