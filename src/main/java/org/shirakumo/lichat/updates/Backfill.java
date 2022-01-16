// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Backfill extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("backfill", "shirakumo");
        CL.registerClass(className, Backfill.class);
    }

    public long since = 0;

    public Backfill(Map<String, Object> initargs){
        super(initargs);
        since = (long)CL.arg(initargs, "since");
    }
}
