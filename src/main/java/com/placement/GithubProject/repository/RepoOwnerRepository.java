package com.placement.GithubProject.repository;

import com.placement.GithubProject.entity.RepoOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoOwnerRepository extends JpaRepository<RepoOwner,Long> {


}
