package com.placement.GithubProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException ex) {

        return new ResponseEntity<>(ex.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> noHandlerExceptionFound(NoHandlerFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GitRepoAlreadyExistException.class)
    public ResponseEntity<String> gitRepoExistException(GitRepoAlreadyExistException gitRepoAlreadyExistException){
        return new ResponseEntity<>(gitRepoAlreadyExistException.getMessage(),HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(GitRepoNotFoundException.class)
    public ResponseEntity<String> gitRepoNotFoundException(GitRepoNotFoundException gitRepoNotFoundException){
        return new ResponseEntity<>(gitRepoNotFoundException.getMessage(),HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(OwnerNotFoundException.class)
    public ResponseEntity<String> ownerNotFound(OwnerNotFoundException ownerNotFoundException){
        return new ResponseEntity<>(ownerNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OwnerAlreadyExistException.class)
    public ResponseEntity<String> ownerExistException(OwnerAlreadyExistException ownerAlreadyExistException){
        return new ResponseEntity<>(ownerAlreadyExistException.getMessage(),HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> commonException(Exception exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.ALREADY_REPORTED);
    }



}
