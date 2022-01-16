// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;
import java.util.*;

public class Users extends ChannelUpdate{
    public static final Symbol className;
    static{
        className = CL.intern("users", "lichat");
        CL.registerClass(className, Users.class);
    }

    public List<String> users = new ArrayList<String>();

    public Users(Map<String, Object> initargs){
        super(initargs);
        users = (List<String>)CL.arg(initargs, "users");
    }
}
