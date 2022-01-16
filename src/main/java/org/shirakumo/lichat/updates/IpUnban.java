// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class IpUnban extends Update{
    public static final Symbol className;
    static{
        className = CL.intern("ip-unban", "shirakumo");
        CL.registerClass(className, IpUnban.class);
    }

    public String ip = null;
    public String mask = null;

    public IpUnban(Map<String, Object> initargs){
        super(initargs);
        ip = (String)CL.requiredArg(initargs, "ip");
        mask = (String)CL.requiredArg(initargs, "mask");
    }
}
