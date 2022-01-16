// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class IpBlacklist extends Update{
    public static final Symbol className;
    static{
        className = CL.intern("ip-blacklist", "shirakumo");
        CL.registerClass(className, IpBlacklist.class);
    }

    public List<List<String>> target = new ArrayList<List<String>>();

    public IpBlacklist(Map<String, Object> initargs){
        super(initargs);
        target = (List<List<String>>)CL.arg(initargs, "target");
    }
}
