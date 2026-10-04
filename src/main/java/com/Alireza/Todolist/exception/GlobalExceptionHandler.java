package com.Alireza.Todolist.exception;

import com.Alireza.Todolist.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> emailDuplication(EmailAlreadyExistsException emailAlreadyExistsException){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(emailAlreadyExistsException.getMessage()));
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> taskNotFound(TaskNotFoundException taskNotFoundException){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(taskNotFoundException.getMessage()) );
    }

}
