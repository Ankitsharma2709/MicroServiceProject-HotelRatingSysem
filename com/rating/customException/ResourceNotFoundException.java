package com.rating.customException;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super("Resource not found exception occur !!");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
