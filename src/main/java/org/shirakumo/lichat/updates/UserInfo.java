// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class UserInfo extends TargetUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("user-info", "lichat");
        CL.registerClass(className, UserInfo.class);
    }

    public boolean registered = false;
    public long connections = 0;
    public List<Object> info = new ArrayList<Object>();

    public UserInfo(Map<String, Object> initargs){
        super(initargs);
        registered = (boolean)CL.arg(initargs, "registered");
        connections = (long)CL.arg(initargs, "connections");
        info = (List<Object>)CL.arg(initargs, "info");
    }
}
