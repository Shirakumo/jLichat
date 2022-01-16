// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class BadName extends UpdateFailure{
    public static final Symbol className;
    static{
        className = CL.intern("bad-name", "lichat");
        CL.registerClass(className, BadName.class);
    }


    public BadName(Map<String, Object> initargs){
        super(initargs);
    }
}
