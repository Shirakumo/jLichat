// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Pull extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("pull", "lichat");
        CL.registerClass(className, Pull.class);
    }

    public String target = null;

    public Pull(Map<String, Object> initargs){
        super(initargs);
        target = (String)CL.arg(initargs, "target");
    }
}
