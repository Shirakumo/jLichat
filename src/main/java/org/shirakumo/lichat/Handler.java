package org.shirakumo.lichat;
import org.shirakumo.lichat.updates.*;

public interface Handler{
    public void handle(Update update);
    public void onConnectionLost(Exception ex);
}
