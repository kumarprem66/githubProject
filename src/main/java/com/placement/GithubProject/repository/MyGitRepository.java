package com.placement.GithubProject.repository;

import com.placement.GithubProject.entity.MyGit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyGitRepository extends JpaRepository<MyGit,Long> {
}
