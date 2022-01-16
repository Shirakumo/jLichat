// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class AlreadyInChannel extends UpdateFailure{
    public static final Symbol className;
    static{
        className = CL.intern("already-in-channel", "lichat");
        CL.registerClass(className, AlreadyInChannel.class);
    }


    public AlreadyInChannel(Map<String, Object> initargs){
        super(initargs);
    }
}
