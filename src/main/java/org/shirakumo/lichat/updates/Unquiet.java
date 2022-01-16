// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Unquiet extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("unquiet", "shirakumo");
        CL.registerClass(className, Unquiet.class);
    }

    public String target = null;

    public Unquiet(Map<String, Object> initargs){
        super(initargs);
        target = (String)CL.arg(initargs, "target");
    }
}
