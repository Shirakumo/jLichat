// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Connect extends Update{
    public static final Symbol className;
    static{
        className = CL.intern("connect", "lichat");
        CL.registerClass(className, Connect.class);
    }

    public String version = null;
    public String password = null;
    public List<String> extensions = new ArrayList<String>();

    public Connect(Map<String, Object> initargs){
        super(initargs);
        version = (String)CL.requiredArg(initargs, "version");
        password = (String)CL.arg(initargs, "password");
        extensions = (List<String>)CL.requiredArg(initargs, "extensions");
    }
}
