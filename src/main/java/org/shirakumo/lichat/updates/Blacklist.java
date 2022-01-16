// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Blacklist extends Update{
    public static final Symbol className;
    static{
        className = CL.intern("blacklist", "shirakumo");
        CL.registerClass(className, Blacklist.class);
    }

    public List<String> target = new ArrayList<String>();

    public Blacklist(Map<String, Object> initargs){
        super(initargs);
        target = (List<String>)CL.arg(initargs, "target");
    }
}
