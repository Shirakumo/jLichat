// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class NoSuchUser extends UpdateFailure{
    public static final Symbol className;
    static{
        className = CL.intern("no-such-user", "lichat");
        CL.registerClass(className, NoSuchUser.class);
    }


    public NoSuchUser(Map<String, Object> initargs){
        super(initargs);
    }
}
