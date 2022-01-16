// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class UpdateTooLong extends Failure{
    public static final Symbol className;
    static{
        className = CL.intern("update-too-long", "lichat");
        CL.registerClass(className, UpdateTooLong.class);
    }


    public UpdateTooLong(Map<String, Object> initargs){
        super(initargs);
    }
}
