package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Update extends StandardObject{
    public static final Symbol className;
    static{
        className = CL.findSymbol("UPDATE");
        CL.registerClass(className, Update.class);
    }

    public final long clock;
    public final Object id;
    public final String from;

    public Update(Map<String, Object> initargs){
        super(initargs);
        clock = (Long)CL.requiredArg(initargs, "clock");
        id = (Object)CL.requiredArg(initargs, "id");
        from = (String)CL.requiredArg(initargs, "from");
    }

    private static int IdCounter = 0;
    public static int nextId(){
        return IdCounter++;
    }
}
