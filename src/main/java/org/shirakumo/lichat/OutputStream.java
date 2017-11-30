package org.shirakumo.lichat;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class OutputStream{
    private final OutputStreamWriter writer;

    public OutputStream(java.io.OutputStream os){
        OutputStreamWriter writer = null;
        try{
            writer = new OutputStreamWriter(os, "utf8");
        }catch(Exception ex){
            CL.error("ENCODING-UNSUPPORTED", "Your system doesn't support UTF-8.");
        }
        this.writer = writer;
    }

    public void write(int c){
        try{
            writer.write(c);
        }catch(Exception ex){
            CL.error("WRITE-ERROR", ex.toString());
        }
    }

    public void write(String s){
        try{
            writer.write(s, 0, s.length());
        }catch(Exception ex){
            CL.error("WRITE-ERROR", ex.toString());
        }
    }

    public void flush(){
        try{
            writer.flush();
        }catch(Exception ex){
            CL.error("WRITE-ERROR", ex.toString());
        }
    }

    public void close(){
        try{
            writer.close();
        }catch(IOException ex){}
    }
}
