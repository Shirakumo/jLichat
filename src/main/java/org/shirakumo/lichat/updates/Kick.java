// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Kick extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("kick", "lichat");
        CL.registerClass(className, Kick.class);
    }

    public String target = null;

    public Kick(Map<String, Object> initargs){
        super(initargs);
        target = (String)CL.arg(initargs, "target");
    }
}
