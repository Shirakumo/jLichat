package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Create extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.findSymbol("CREATE");
        CL.registerClass(className, Create.class);
    }

    public Create(Map<String, Object> initargs){
        super(initargs);
        
    }
}
