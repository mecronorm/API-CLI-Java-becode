package org.cogip.cogiprestapi.Exeptions;

public class MissingParametersException extends RuntimeException{
    public MissingParametersException(String message) {
        super(message);
    }

    public MissingParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingParametersException(Throwable cause) {
        super(cause);
    }
}
