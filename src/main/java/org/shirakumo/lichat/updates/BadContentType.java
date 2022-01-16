// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class BadContentType extends UpdateFailure{
    public static final Symbol className;
    static{
        className = CL.intern("bad-content-type", "shirakumo");
        CL.registerClass(className, BadContentType.class);
    }

    public List<String> allowedContentTypes = new ArrayList<String>();

    public BadContentType(Map<String, Object> initargs){
        super(initargs);
        allowedContentTypes = (List<String>)CL.requiredArg(initargs, "allowed-content-types");
    }
}
