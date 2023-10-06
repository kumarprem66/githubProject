package com.placement.GithubProject.exception;

public class GitRepoAlreadyExistException extends RuntimeException{

    GitRepoAlreadyExistException(){

    }

    GitRepoAlreadyExistException(String message){
        super(message);
    }

}
