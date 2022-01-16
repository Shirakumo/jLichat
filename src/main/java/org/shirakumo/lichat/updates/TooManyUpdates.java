// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class TooManyUpdates extends UpdateFailure{
    public static final Symbol className;
    static{
        className = CL.intern("too-many-updates", "lichat");
        CL.registerClass(className, TooManyUpdates.class);
    }


    public TooManyUpdates(Map<String, Object> initargs){
        super(initargs);
    }
}
