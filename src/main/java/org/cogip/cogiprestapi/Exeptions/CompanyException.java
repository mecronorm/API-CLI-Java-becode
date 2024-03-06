package org.cogip.cogiprestapi.Exeptions;

import org.springframework.http.HttpStatus;

public class CompanyException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus status;


    public CompanyException(String message, Throwable throwable, HttpStatus status) {
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
