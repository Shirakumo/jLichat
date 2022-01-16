// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class ChannelnameTaken extends UpdateFailure{
    public static final Symbol className;
    static{
        className = CL.intern("channelname-taken", "lichat");
        CL.registerClass(className, ChannelnameTaken.class);
    }


    public ChannelnameTaken(Map<String, Object> initargs){
        super(initargs);
    }
}
