// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Data extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("data", "shirakumo");
        CL.registerClass(className, Data.class);
    }

    public String contentType = null;
    public String payload = null;
    public String filename = null;

    public Data(Map<String, Object> initargs){
        super(initargs);
        contentType = (String)CL.requiredArg(initargs, "content-type");
        payload = (String)CL.requiredArg(initargs, "payload");
        filename = (String)CL.arg(initargs, "filename");
    }
}
