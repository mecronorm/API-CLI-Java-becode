package org.cogip.cogiprestapi.Exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(value = {IdNotFoundException.class})
    public ResponseEntity<Object> handelIdNotFoundException(IdNotFoundException idNotFoundException){
        CompanyException companyException = new CompanyException(idNotFoundException.getMessage(),idNotFoundException.getCause(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(companyException, HttpStatus.NOT_FOUND);
    }

}