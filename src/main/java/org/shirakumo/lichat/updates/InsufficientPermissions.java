package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class InsufficientPermissions extends UpdateFailure{
    public static final Symbol className;
    static{
        className = CL.findSymbol("INSUFFICIENT-PERMISSIONS");
        CL.registerClass(className, InsufficientPermissions.class);
    }

    public InsufficientPermissions(Map<String, Object> initargs){
        super(initargs);
        
    }
}
