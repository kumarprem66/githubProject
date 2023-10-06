package com.placement.GithubProject.service;

import com.placement.GithubProject.entity.MyGit;

public interface MyGitOps {

     MyGit getGitRepoById(Long id);

     MyGit saveGitRepo(MyGit repo);

}
