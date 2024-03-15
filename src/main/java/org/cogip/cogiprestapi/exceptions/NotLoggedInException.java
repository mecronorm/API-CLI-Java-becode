package org.cogip.cogiprestapi.exceptions;

public class NotLoggedInException extends RuntimeException{

    public NotLoggedInException(String message) {
        super(message);
    }

    public NotLoggedInException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotLoggedInException(Throwable cause) {
        super(cause);
    }
}
