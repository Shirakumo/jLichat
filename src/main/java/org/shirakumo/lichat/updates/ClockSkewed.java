// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class ClockSkewed extends UpdateFailure{
    public static final Symbol className;
    static{
        className = CL.intern("clock-skewed", "lichat");
        CL.registerClass(className, ClockSkewed.class);
    }


    public ClockSkewed(Map<String, Object> initargs){
        super(initargs);
    }
}
