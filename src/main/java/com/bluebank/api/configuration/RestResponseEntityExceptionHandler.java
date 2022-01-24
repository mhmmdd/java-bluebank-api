package com.bluebank.api.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = InvalidInsuranceAmountException.class)
    public ResponseEntity<Object> handleBusinessExceptions(InvalidInsuranceAmountException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundExceptions(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage() + " Entity not found", HttpStatus.NOT_FOUND);
    }

}
