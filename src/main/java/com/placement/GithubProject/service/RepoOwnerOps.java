package com.placement.GithubProject.service;


import com.placement.GithubProject.entity.MyGit;
import com.placement.GithubProject.entity.RepoOwner;

public interface RepoOwnerOps {


    RepoOwner getRepoOwner(Long id);


    RepoOwner saveRepoOwner(RepoOwner repo);
}
