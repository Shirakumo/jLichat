// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class IncompatibleVersion extends UpdateFailure{
    public static final Symbol className;
    static{
        className = CL.intern("incompatible-version", "lichat");
        CL.registerClass(className, IncompatibleVersion.class);
    }

    public List<String> compatibleVersions = new ArrayList<String>();

    public IncompatibleVersion(Map<String, Object> initargs){
        super(initargs);
        compatibleVersions = (List<String>)CL.requiredArg(initargs, "compatible-versions");
    }
}
