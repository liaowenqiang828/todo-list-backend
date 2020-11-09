package com.thoughtworks.probation.todoList.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IdNotMatchedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResult> handler(IdNotMatchedException exception) {
        String message = exception.getMessage();
        ErrorResult errorResult = new ErrorResult(message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }
}
