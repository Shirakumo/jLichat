// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Blocked extends Update{
    public static final Symbol className;
    static{
        className = CL.intern("blocked", "shirakumo");
        CL.registerClass(className, Blocked.class);
    }

    public List<String> target = new ArrayList<String>();

    public Blocked(Map<String, Object> initargs){
        super(initargs);
        target = (List<String>)CL.arg(initargs, "target");
    }
}
