// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Capabilities extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("capabilities", "lichat");
        CL.registerClass(className, Capabilities.class);
    }

    public List<Symbol> permitted = new ArrayList<Symbol>();

    public Capabilities(Map<String, Object> initargs){
        super(initargs);
        permitted = (List<Symbol>)CL.arg(initargs, "permitted");
    }
}
