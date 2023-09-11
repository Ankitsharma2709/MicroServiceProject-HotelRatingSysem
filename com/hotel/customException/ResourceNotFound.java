package com.hotel.customException;

import org.springframework.web.servlet.config.annotation.ResourceChainRegistration;

public class ResourceNotFound extends  RuntimeException{
    private static final long serialVersionUID = 1L;
    public ResourceNotFound(){
        super("Resource is not found n the server !!");
    }
    public ResourceNotFound(String message){
        super(message);
    }
}
