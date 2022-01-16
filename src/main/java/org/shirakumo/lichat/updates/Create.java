// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Create extends Update{
    public static final Symbol className;
    static{
        className = CL.intern("create", "lichat");
        CL.registerClass(className, Create.class);
    }

    public String channel = null;

    public Create(Map<String, Object> initargs){
        super(initargs);
        channel = (String)CL.arg(initargs, "channel");
    }
}
