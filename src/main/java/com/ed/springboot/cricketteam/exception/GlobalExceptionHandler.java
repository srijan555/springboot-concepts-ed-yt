package com.ed.springboot.cricketteam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String,String> errors=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName=((FieldError)error).getField();
            String message= error.getDefaultMessage();
            errors.put(fieldName,message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    //Custom PlayerNotFoundException
    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handlePlayerNotFound(PlayerNotFoundException ex){
        Map<String,Object> response=new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("error",ex.getMessage());
        response.put("status",HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    //Generic Exception (catch-all)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleGlobalExceptions(Exception ex){
        Map<String,Object> response=new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("error",ex.getMessage());
        response.put("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
