// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Update extends StandardObject{
    public static final Symbol className;
    static{
        className = CL.intern("update", "lichat");
        CL.registerClass(className, Update.class);
    }

    public long clock = 0;
    public String from = null;
    public String signature = null;
    public long id = 0;

    public Update(Map<String, Object> initargs){
        super(initargs);
        clock = (long)CL.arg(initargs, "clock");
        from = (String)CL.arg(initargs, "from");
        signature = (String)CL.arg(initargs, "signature");
        id = (long)CL.requiredArg(initargs, "id");
    }
}
