package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(PersonNotFoundExcepttion.class)
    public ResponseEntity<ApiError> handlerPersonNotFoundException(PersonNotFoundExcepttion e){

        ApiError apiError = new ApiError();
        apiError.setMessage(e.getMessage());
        apiError.setCode(HttpStatus.NOT_FOUND.value());
        apiError.setTime(LocalDateTime.now());

        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CommandeNotFoundException.class)
    public ResponseEntity<ApiError> handlerCommandeNotFoundException(CommandeNotFoundException e){
        ApiError apiError = new ApiError();
        apiError.setMessage(e.getMessage());
        apiError.setCode(HttpStatus.NOT_FOUND.value());
        apiError.setTime(LocalDateTime.now());

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handlerException(Exception e){

        ApiError apiError = new ApiError();
        apiError.setMessage(e.getMessage());
        apiError.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiError.setTime(LocalDateTime.now());

        return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Pour n'importe qu'elle autre exception
}
