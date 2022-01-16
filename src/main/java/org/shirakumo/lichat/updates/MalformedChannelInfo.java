// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class MalformedChannelInfo extends UpdateFailure{
    public static final Symbol className;
    static{
        className = CL.intern("malformed-channel-info", "shirakumo");
        CL.registerClass(className, MalformedChannelInfo.class);
    }


    public MalformedChannelInfo(Map<String, Object> initargs){
        super(initargs);
    }
}
