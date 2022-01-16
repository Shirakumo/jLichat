// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Quieted extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("quieted", "shirakumo");
        CL.registerClass(className, Quieted.class);
    }

    public List<String> target = new ArrayList<String>();

    public Quieted(Map<String, Object> initargs){
        super(initargs);
        target = (List<String>)CL.arg(initargs, "target");
    }
}
