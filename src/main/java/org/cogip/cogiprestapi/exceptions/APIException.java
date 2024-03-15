package org.cogip.cogiprestapi.exceptions;

import org.springframework.http.HttpStatus;

public class APIException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus status;


    public APIException(String message, Throwable throwable, HttpStatus status) {
        this.message = message;
        this.throwable = throwable;
        this.status = status;
    }
    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public HttpStatus getStatus() {
        return status;
    }


}
