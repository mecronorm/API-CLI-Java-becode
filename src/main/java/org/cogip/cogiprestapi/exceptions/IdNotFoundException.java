package org.cogip.cogiprestapi.exceptions;


public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
    public IdNotFoundException(String message){
        super(message);
    }
    public IdNotFoundException(Throwable cause){
        super(cause);
    }
}