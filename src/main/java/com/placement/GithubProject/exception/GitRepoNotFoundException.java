package com.placement.GithubProject.exception;

public class GitRepoNotFoundException extends RuntimeException{

    public GitRepoNotFoundException(){}
    public GitRepoNotFoundException(String message){
        super(message);
    }

}
