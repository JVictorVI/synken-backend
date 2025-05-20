package com.faden.synken_backend.exceptions;

import com.faden.synken_backend.exceptions.post.PostNotFoundException;
import com.faden.synken_backend.exceptions.user.EmailAlreadyExistsException;
import com.faden.synken_backend.exceptions.user.UserNotFoundException;
import com.faden.synken_backend.exceptions.user.UserWrongPasswordException;
import com.faden.synken_backend.exceptions.user.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    ResponseEntity<String> handleMethodNotFound(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body("O método requisitado " + ex.getMethod() + " não está definido neste endpoint.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body("O campo " + ex.getFieldError() + " não pode estar vazio.");
    }
}
