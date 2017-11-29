package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Message extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.findSymbol("MESSAGE");
        CL.registerClass(className, Message.class);
    }

    public Message(Map<String, Object> initargs){
        super(initargs);
        
    }
}
