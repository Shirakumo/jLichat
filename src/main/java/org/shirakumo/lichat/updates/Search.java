// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Search extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("search", "shirakumo");
        CL.registerClass(className, Search.class);
    }

    public List<Object> results = new ArrayList<Object>();
    public List<Object> query = new ArrayList<Object>();
    public long offset = 0;

    public Search(Map<String, Object> initargs){
        super(initargs);
        results = (List<Object>)CL.arg(initargs, "results");
        query = (List<Object>)CL.arg(initargs, "query");
        offset = (long)CL.arg(initargs, "offset");
    }
}
