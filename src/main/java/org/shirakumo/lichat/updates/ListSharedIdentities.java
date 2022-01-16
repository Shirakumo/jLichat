// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class ListSharedIdentities extends Update{
    public static final Symbol className;
    static{
        className = CL.intern("list-shared-identities", "shirakumo");
        CL.registerClass(className, ListSharedIdentities.class);
    }

    public List<Object> identities = new ArrayList<Object>();

    public ListSharedIdentities(Map<String, Object> initargs){
        super(initargs);
        identities = (List<Object>)CL.arg(initargs, "identities");
    }
}
