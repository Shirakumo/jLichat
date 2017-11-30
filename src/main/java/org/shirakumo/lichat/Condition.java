package org.shirakumo.lichat;

public class Condition extends RuntimeException{
    private final String message;
    private final String type;

    public Condition(String type){
        this.type = type;
        this.message = this.toString();
    }
    
    public Condition(String type, String message){
        this.type = type;
        this.message = message;
    }

    public String toString(){
        return "[Condition of type "+type+"] "+message;
    }

    public String report(){
        return message;
    }
}
