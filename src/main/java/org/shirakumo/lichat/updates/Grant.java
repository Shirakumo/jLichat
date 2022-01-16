// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Grant extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("grant", "lichat");
        CL.registerClass(className, Grant.class);
    }

    public Symbol update = null;
    public String target = null;

    public Grant(Map<String, Object> initargs){
        super(initargs);
        update = (Symbol)CL.requiredArg(initargs, "update");
        target = (String)CL.arg(initargs, "target");
    }
}
