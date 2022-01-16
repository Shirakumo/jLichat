// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Pause extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("pause", "shirakumo");
        CL.registerClass(className, Pause.class);
    }

    public long by = 0;

    public Pause(Map<String, Object> initargs){
        super(initargs);
        by = (long)CL.requiredArg(initargs, "by");
    }
}
