package com.placement.GithubProject.exception;

public class OwnerAlreadyExistException extends RuntimeException{

    public OwnerAlreadyExistException(){}
    public OwnerAlreadyExistException(String message){
        super(message);
    }

}
