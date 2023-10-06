package com.placement.GithubProject.exception;

public class OwnerNotFoundException extends RuntimeException{


    public OwnerNotFoundException(){}

    public OwnerNotFoundException(String message){
        super(message);
    }

}
