// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class UsernameMismatch extends UpdateFailure{
    public static final Symbol className;
    static{
        className = CL.intern("username-mismatch", "lichat");
        CL.registerClass(className, UsernameMismatch.class);
    }


    public UsernameMismatch(Map<String, Object> initargs){
        super(initargs);
    }
}
