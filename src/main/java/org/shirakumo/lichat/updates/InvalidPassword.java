// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class InvalidPassword extends UpdateFailure{
    public static final Symbol className;
    static{
        className = CL.intern("invalid-password", "lichat");
        CL.registerClass(className, InvalidPassword.class);
    }


    public InvalidPassword(Map<String, Object> initargs){
        super(initargs);
    }
}
