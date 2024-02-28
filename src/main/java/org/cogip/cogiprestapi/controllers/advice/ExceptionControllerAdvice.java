package org.cogip.cogiprestapi.controllers.advice;

import org.cogip.cogiprestapi.exceptions.IdDoesNotExistException;
import org.cogip.cogiprestapi.model.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
  
  @ExceptionHandler (IdDoesNotExistException.class)
  public ResponseEntity<ErrorDetails> exceptionIdDoesNotExistHandler(){
    ErrorDetails errorDetails = new ErrorDetails();
    errorDetails.setErrorMessage("This id does not exist");
    
    return ResponseEntity
            .badRequest()
            .body(errorDetails);
  }
}
