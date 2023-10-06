package com.placement.GithubProject.service;

import com.placement.GithubProject.entity.MyGit;
import com.placement.GithubProject.entity.RepoOwner;
import com.placement.GithubProject.exception.GitRepoNotFoundException;
import com.placement.GithubProject.exception.OwnerNotFoundException;
import com.placement.GithubProject.repository.MyGitRepository;
import com.placement.GithubProject.repository.RepoOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class MyGitImplementation implements MyGitOps{

    @Autowired
    private MyGitRepository gitRepository;

    @Autowired
    private RepoOwnerRepository repoOwnerRepository;
    @Autowired
    private RestTemplate restTemplate;


    @Override
    public MyGit getGitRepoById(Long id) {

       Optional<MyGit> optionalMyGit  = gitRepository.findById(id);
       if(optionalMyGit.isPresent()){
           return optionalMyGit.get();
       }
       throw new GitRepoNotFoundException("Repository is not found with this id");
    }

    @Override
    public MyGit saveGitRepo(MyGit repo) {



        return gitRepository.save(repo);

    }

    public MyGit fetchAndSaveGitHubData(String githubApiUrl) {
            try {

                String authToken = "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";

                // Create HTTP headers with the Authorization header containing the token
                HttpHeaders headers = new HttpHeaders();
                headers.setBearerAuth(authToken);
                headers.setContentType(MediaType.APPLICATION_JSON);

                // Create a HttpEntity with the headers
                HttpEntity<String> entity = new HttpEntity<>(headers);


                // Define the URL of the GitHub API endpoint for the repository
//                String gitUrl = "https://api.github.com/repos/kumarprem66/FoodCorner";



                ResponseEntity<MyGit> responseEntity = restTemplate.exchange(
                        githubApiUrl, HttpMethod.GET, entity, MyGit.class);

                // Check the response status code
                HttpStatus statusCode = (HttpStatus) responseEntity.getStatusCode();

                if (statusCode == HttpStatus.OK) {
                    // The request was successful, and you can access the CustomGitHubData object


                    MyGit customGitHubData = responseEntity.getBody();
                    assert customGitHubData != null;
                    repoOwnerRepository.save(customGitHubData.getOwner());
                    saveGitRepo(customGitHubData);
                    // Print or work with the CustomGitHubData object as needed
//                    assert customGitHubData != null;
//                    System.out.println("GitHub Repository Name: " + customGitHubData.getName());
                    return customGitHubData;
                    // Access other properties as well
                } else {
                    // Handle error cases, e.g., print an error message
//                    System.err.println("Error: " + statusCode);
                    throw new RuntimeException("Failed to fetch data from GitHub API. Status code: " + responseEntity.getStatusCode());
                }

            } catch (RestClientException e) {
                // Handle REST client exceptions (e.g., network issues, invalid URL)
                throw new RuntimeException("Failed to fetch data from GitHub API: " + e.getMessage(), e);
            } catch (Exception e) {
                // Handle other exceptions
                throw new RuntimeException("An error occurred while fetching and saving data: " + e.getMessage(), e);
            }



    }

}
