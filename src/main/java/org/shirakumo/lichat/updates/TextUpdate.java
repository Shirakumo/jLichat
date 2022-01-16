// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class TextUpdate extends Update{
    public static final Symbol className;
    static{
        className = CL.intern("text-update", "lichat");
        CL.registerClass(className, TextUpdate.class);
    }

    public String markup = null;
    public String text = null;
    public String rich = null;

    public TextUpdate(Map<String, Object> initargs){
        super(initargs);
        markup = (String)CL.arg(initargs, "markup");
        text = (String)CL.requiredArg(initargs, "text");
        rich = (String)CL.arg(initargs, "rich");
    }
}
