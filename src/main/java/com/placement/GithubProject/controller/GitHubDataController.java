package com.placement.GithubProject.controller;

import com.placement.GithubProject.entity.MyGit;
import com.placement.GithubProject.service.MyGitImplementation;
import com.placement.GithubProject.utility.GitHubDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/github")
public class GitHubDataController {

    @Autowired
    private MyGitImplementation gitHubDataService;

    @PostMapping
    public ResponseEntity<?> fetchAndSaveGitHubData(@RequestBody GitHubDataRequest request) {
        try {

            MyGit myGit = gitHubDataService.fetchAndSaveGitHubData(request.getUrl());

            return ResponseEntity.ok(myGit);
        } catch (Exception e) {
            // Handle errors and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGitHubDataById(@PathVariable Long id) {
        try {
            MyGit gitHubData = gitHubDataService.getGitRepoById(id);
            if (gitHubData != null) {
                return ResponseEntity.ok(gitHubData);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }
}

