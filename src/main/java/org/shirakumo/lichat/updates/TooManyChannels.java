// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class TooManyChannels extends UpdateFailure{
    public static final Symbol className;
    static{
        className = CL.intern("too-many-channels", "lichat");
        CL.registerClass(className, TooManyChannels.class);
    }


    public TooManyChannels(Map<String, Object> initargs){
        super(initargs);
    }
}
