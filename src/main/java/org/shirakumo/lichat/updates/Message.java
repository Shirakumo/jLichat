// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Message extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("message", "lichat");
        CL.registerClass(className, Message.class);
    }

    public String text = null;
    public List<Object> replyTo = new ArrayList<Object>();
    public String link = null;

    public Message(Map<String, Object> initargs){
        super(initargs);
        text = (String)CL.arg(initargs, "text");
        replyTo = (List<Object>)CL.arg(initargs, "reply-to");
        link = (String)CL.arg(initargs, "link");
    }
}
