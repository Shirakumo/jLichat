// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class UpdateFailure extends Failure{
    public static final Symbol className;
    static{
        className = CL.intern("update-failure", "lichat");
        CL.registerClass(className, UpdateFailure.class);
    }

    public long updateId = 0;

    public UpdateFailure(Map<String, Object> initargs){
        super(initargs);
        updateId = (long)CL.requiredArg(initargs, "update-id");
    }
}
