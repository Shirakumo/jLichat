// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Permissions extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("permissions", "lichat");
        CL.registerClass(className, Permissions.class);
    }

    public List<List<Object>> permissions = new ArrayList<List<Object>>();

    public Permissions(Map<String, Object> initargs){
        super(initargs);
        permissions = (List<List<Object>>)CL.arg(initargs, "permissions");
    }
}
