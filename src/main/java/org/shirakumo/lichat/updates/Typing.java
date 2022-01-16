// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Typing extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("typing", "shirakumo");
        CL.registerClass(className, Typing.class);
    }


    public Typing(Map<String, Object> initargs){
        super(initargs);
    }
}
