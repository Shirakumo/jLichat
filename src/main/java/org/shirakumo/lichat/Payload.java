package org.shirakumo.lichat;
import org.shirakumo.lichat.updates.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class Payload{
    public final String name;
    public final String contentType;
    public final byte[] data;

    public Payload(Emote update){
        this(update.name, update.contentType, update.payload);
    }

    public Payload(Data update){
        this(update.filename, update.contentType, update.payload);
    }

    public Payload(String name, String contentType, String data){
        this(name, contentType, Base64.getDecoder().decode(data));
    }

    public Payload(String name, String contentType, byte[] data){
        this.name = name;
        this.contentType = contentType;
        this.data = data;
    }

    public Payload(String path) throws IOException{
        this(Paths.get(path));
    }

    public Payload(Path path) throws IOException{
        String filname = path.getFileName().toString();
        name = filname.substring(0, filname.lastIndexOf('.'));
        contentType = Files.probeContentType(path);
        data = Files.readAllBytes(path);
    }

    public void save(String path) throws IOException{
        save(Paths.get(path));
    }

    public void save(Path path) throws IOException{
        Files.write(path, data);
    }
}
