package org.shirakumo.lichat;
import java.io.InputStreamReader;
import java.io.IOException;

public class InputStream{
    private final InputStreamReader reader;
    private int buffer = -1;
    
    public InputStream(java.io.InputStream is){
        InputStreamReader reader = null;
        try{
            reader = new InputStreamReader(is, "utf8");
        }catch(Exception ex){
            CL.error("ENCODING-UNSUPPORTED", "Your system doesn't support UTF-8.");
        }
        this.reader = reader;
    }

    public boolean hasMore(){
        if(buffer == -1){
            try{
                buffer = reader.read();
                if(buffer != -1)
                    System.out.print(new String(Character.toChars(buffer)));
            }catch(IOException ex){
                buffer = -1;
            }
            return (buffer != -1);
        }else{
            return true;
        }
    }

    public int peek(){
        int c = peekNoError();
        if(buffer == -1) CL.error("END-OF-STREAM");
        return c;
    }

    public int peekNoError(){
        if(buffer == -1){
            try{
                buffer = reader.read();
                if(buffer != -1)
                    System.out.print(new String(Character.toChars(buffer)));
            }catch(IOException ex){
                buffer = -1;
            }
        }
        return buffer;
    }

    public int read(){
        int c = readNoError();
        if(c == -1) CL.error("END-OF-STREAM");
        return c;
    }

    public int readNoError(){
        if(buffer == -1){
            try{
                int c = reader.read();
                if(c != -1)
                    System.out.print(new String(Character.toChars(c)));
                if(c == 0) System.out.println();
                return c;
            }catch(IOException ex){
                return -1;
            }
        }else{
            int b = buffer;
            buffer = -1;
            return b;
        }
    }

    public void unread(int c){
        buffer = c;
    }

    public void close(){
        try{
            reader.close();
        }catch(IOException ex){}
    }
}
