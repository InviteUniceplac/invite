package com.invite.api.exception;

public class ResourceNotFoundException extends RuntimeException{
    private static long serialVersionUID = 1L;

    public ResourceNotFoundException(Object id){
        super("Resouce not found. Id: " + id);
    }

}
