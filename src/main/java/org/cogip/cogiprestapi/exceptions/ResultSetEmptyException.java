package org.cogip.cogiprestapi.exceptions;

public class ResultSetEmptyException extends RuntimeException{

    public ResultSetEmptyException(String message) {
        super(message);
    }

    public ResultSetEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResultSetEmptyException(Throwable cause) {
        super(cause);
    }
}
