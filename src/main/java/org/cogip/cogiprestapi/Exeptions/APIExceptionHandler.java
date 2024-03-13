package org.cogip.cogiprestapi.Exeptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.cogip.cogiprestapi.enums.CompanyType;
import org.cogip.cogiprestapi.enums.InvoiceCurrency;
import org.cogip.cogiprestapi.enums.InvoiceStatus;
import org.cogip.cogiprestapi.enums.InvoiceType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(value = {IdNotFoundException.class})
    public ResponseEntity<Object> handelIdNotFoundException(IdNotFoundException idNotFoundException){
        APIException apiException = new APIException(idNotFoundException.getMessage(),idNotFoundException.getCause(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DuplicateValueException.class})
    public ResponseEntity<Object> handleDuplicateValueException(DuplicateValueException duplicateValueException){
        APIException apiException = new APIException(duplicateValueException.getMessage(),duplicateValueException.getCause(),HttpStatus.CONFLICT);
        return new ResponseEntity<>(apiException, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {InvalidInputException.class})
    public ResponseEntity<Object> handleInvalidInputException(InvalidInputException invalidInputException){
        APIException apiException = new APIException(invalidInputException.getMessage(),invalidInputException.getCause(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MissingParametersException.class})
    public ResponseEntity<Object> handleMissingParametersException(MissingParametersException missingParametersException){
        APIException apiException = new APIException(missingParametersException.getMessage(),missingParametersException.getCause(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotLoggedInException.class})
    public ResponseEntity<Object> handleNotLoggedInException(NotLoggedInException notLoggedInException){
        APIException apiException = new APIException(notLoggedInException.getMessage(),notLoggedInException.getCause(),HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiException,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {ResultSetEmptyException.class})
    public ResponseEntity<Object> handleResultSetEmptyException(ResultSetEmptyException resultSetEmptyException){
        APIException apiException = new APIException(resultSetEmptyException.getMessage(), resultSetEmptyException.getCause(), HttpStatus.SERVICE_UNAVAILABLE);
        return new ResponseEntity<>(apiException, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException accessDeniedException){
        APIException apiException = new APIException(accessDeniedException.getMessage(),accessDeniedException.getCause(),HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiException,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException){
        APIException apiException;
        if (httpMessageNotReadableException.getMessage().contains("CompanyType")){
            apiException = new APIException("type is not an instance of "+ Arrays.toString(CompanyType.values()),null,HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (httpMessageNotReadableException.getMessage().contains("InvoiceCurrency")){
            apiException = new APIException("currency is not an instance of "+ Arrays.toString(InvoiceCurrency.values()),null,HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (httpMessageNotReadableException.getMessage().contains("InvoiceStatus")) {
            apiException = new APIException("status is not an instance of "+Arrays.toString(InvoiceStatus.values()),null,HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (httpMessageNotReadableException.getMessage().contains("InvoiceType")) {
            apiException = new APIException("type is not an instance of "+ Arrays.toString(InvoiceType.values()),null,HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (httpMessageNotReadableException.getMessage().contains("BigDecimal")) {
            apiException = new APIException("value is not a number", null,HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (httpMessageNotReadableException.getMessage().contains("`int`")) {
            apiException = new APIException("company or/and contact id is not a number",null,HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            apiException = new APIException(httpMessageNotReadableException.getMessage(), null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(apiException, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}