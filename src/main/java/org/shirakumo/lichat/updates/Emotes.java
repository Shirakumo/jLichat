package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Emotes extends Update{
    public static final Symbol className;
    static{
        className = CL.intern("EMOTES");
        CL.registerClass(className, Emotes.class);
    }

    public Emotes(Map<String, Object> initargs){
        super(initargs);
        
    }
}
