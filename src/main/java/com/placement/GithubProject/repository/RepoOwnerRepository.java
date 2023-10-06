package com.placement.GithubProject.repository;

import com.placement.GithubProject.entity.RepoOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoOwnerRepository extends JpaRepository<RepoOwner,Long> {

    @Query("SELECT ro FROM RepoOwner ro WHERE ro.html_url = :html_url")
    RepoOwner findByHtmlUrl(@Param("html_url") String html_url);

}
