// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class IpBan extends Update{
    public static final Symbol className;
    static{
        className = CL.intern("ip-ban", "shirakumo");
        CL.registerClass(className, IpBan.class);
    }

    public String ip = null;
    public String mask = null;

    public IpBan(Map<String, Object> initargs){
        super(initargs);
        ip = (String)CL.requiredArg(initargs, "ip");
        mask = (String)CL.requiredArg(initargs, "mask");
    }
}
