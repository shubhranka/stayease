package com.shubhranka.stayease.exceptionHandlers;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ConstraintsViolationHandler {

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<List> handleConstraintViolationException(ConstraintViolationException e) {
        List<String> messages = e.getConstraintViolations().stream()
                .map(constraintViolation -> "Key - " + constraintViolation.getPropertyPath() + " | " + constraintViolation.getMessage())
                .toList();
        return ResponseEntity.badRequest().body(messages);
    }
}
