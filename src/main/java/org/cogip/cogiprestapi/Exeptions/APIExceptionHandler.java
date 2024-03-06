package org.cogip.cogiprestapi.Exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(value = {IdNotFoundException.class})
    public ResponseEntity<Object> handelIdNotFoundException(IdNotFoundException idNotFoundException){
        APIException APIException = new APIException(idNotFoundException.getMessage(),idNotFoundException.getCause(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(APIException, HttpStatus.NOT_FOUND);
    }

}