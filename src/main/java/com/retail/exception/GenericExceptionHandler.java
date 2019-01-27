package com.retail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Generic exception class to handle different exceptions
 * Created by Mahesh Maykarkar on 26/01/19.
 */

@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(NoRecordFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoRecordFoundException(HttpServletRequest req, NoRecordFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ex.getErrorMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RetailValidationException.class)
    public ResponseEntity<ErrorResponse> handleRetailValidationException(HttpServletRequest req, RetailValidationException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        errorResponse.setMessage(ex.getErrorMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
