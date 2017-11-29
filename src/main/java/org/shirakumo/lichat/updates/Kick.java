package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Kick extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.findSymbol("KICK");
        CL.registerClass(className, Kick.class);
    }

    public Kick(Map<String, Object> initargs){
        super(initargs);
        
    }
}
