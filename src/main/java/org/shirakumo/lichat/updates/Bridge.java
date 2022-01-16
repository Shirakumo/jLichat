// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Bridge extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("bridge", "shirakumo");
        CL.registerClass(className, Bridge.class);
    }


    public Bridge(Map<String, Object> initargs){
        super(initargs);
    }
}
