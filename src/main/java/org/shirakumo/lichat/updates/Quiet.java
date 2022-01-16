// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Quiet extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("quiet", "shirakumo");
        CL.registerClass(className, Quiet.class);
    }

    public String target = null;

    public Quiet(Map<String, Object> initargs){
        super(initargs);
        target = (String)CL.arg(initargs, "target");
    }
}
