// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Emotes extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("emotes", "shirakumo");
        CL.registerClass(className, Emotes.class);
    }

    public List<String> names = new ArrayList<String>();

    public Emotes(Map<String, Object> initargs){
        super(initargs);
        names = (List<String>)CL.arg(initargs, "names");
    }
}
