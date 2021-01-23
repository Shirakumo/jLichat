package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Capabilities extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("CAPABILITIES");
        CL.registerClass(className, Capabilities.class);
    }

    public final List<Symbol> updates = new ArrayList<Symbol>();

    public Capabilities(Map<String, Object> initargs){
        super(initargs);
        if(initargs.get("updates") != null)
            updates.addAll((List<Symbol>)initargs.get("updates"));
    }
}
