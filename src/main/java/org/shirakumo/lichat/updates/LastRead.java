// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class LastRead extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("last-read", "shirakumo");
        CL.registerClass(className, LastRead.class);
    }

    public String target = null;
    public long updateId = 0;

    public LastRead(Map<String, Object> initargs){
        super(initargs);
        target = (String)CL.arg(initargs, "target");
        updateId = (long)CL.arg(initargs, "update-id");
    }
}
