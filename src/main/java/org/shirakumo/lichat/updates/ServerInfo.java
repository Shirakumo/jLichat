// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class ServerInfo extends TargetUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("server-info", "lichat");
        CL.registerClass(className, ServerInfo.class);
    }

    public List<List<Object>> attributes = new ArrayList<List<Object>>();
    public List<List<List<Object>>> connections = new ArrayList<List<List<Object>>>();

    public ServerInfo(Map<String, Object> initargs){
        super(initargs);
        attributes = (List<List<Object>>)CL.requiredArg(initargs, "attributes");
        connections = (List<List<List<Object>>>)CL.requiredArg(initargs, "connections");
    }
}
