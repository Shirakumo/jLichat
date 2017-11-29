package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class UpdateTooLong extends Failure{
    public static final Symbol className;
    static{
        className = CL.findSymbol("UPDATE-TOO-LONG");
        CL.registerClass(className, UpdateTooLong.class);
    }

    public UpdateTooLong(Map<String, Object> initargs){
        super(initargs);
        
    }
}
