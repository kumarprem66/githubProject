package com.placement.GithubProject.service;

import com.placement.GithubProject.entity.MyGit;
import com.placement.GithubProject.exception.GitRepoNotFoundException;
import com.placement.GithubProject.repository.MyGitRepository;
import com.placement.GithubProject.repository.RepoOwnerRepository;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class MyGitImplementation implements MyGitOps{

    @Autowired
    private MyGitRepository gitRepository;

    @Autowired
    private RepoOwnerRepository repoOwnerRepository;
    @Autowired
    private RestTemplate restTemplate;


    private final Dotenv dotenv;



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

        MyGit existingGitRepo = gitRepository.findByname(repo.getName());

        if(existingGitRepo != null){
//            updating the repo if it is already exist
            existingGitRepo.setName(repo.getName());
            existingGitRepo.setDescription(repo.getDescription());
            existingGitRepo.setCreated_at(repo.getCreated_at());
            existingGitRepo.setHtml_url(repo.getHtml_url());
            existingGitRepo.setWatchers(repo.getWatchers());
            existingGitRepo.setOpen_issues(repo.getOpen_issues());

            return existingGitRepo;
        }else{
           return gitRepository.save(repo);
        }



    }

    public MyGitImplementation(){

        dotenv = Dotenv.configure().load();
    }


    public MyGit fetchAndSaveGitHubData(String githubApiUrl) {
            try {

                String authToken = dotenv.get("GITHUB_API_TOKEN");

                // Create HTTP headers with the Authorization header containing the token
                HttpHeaders headers = new HttpHeaders();
                headers.setBearerAuth(authToken);
                headers.setContentType(MediaType.APPLICATION_JSON);

                // Create a HttpEntity with the headers
                HttpEntity<String> entity = new HttpEntity<>(headers);


                ResponseEntity<MyGit> responseEntity = restTemplate.exchange(
                        githubApiUrl, HttpMethod.GET, entity, MyGit.class);

                // Check the response status code
                HttpStatus statusCode = (HttpStatus) responseEntity.getStatusCode();

                if (statusCode == HttpStatus.OK) {
                    // The request was successful, and you can access the CustomGitHubData object


                    MyGit customGitHubData = responseEntity.getBody();
                    assert customGitHubData != null;
                    if(repoOwnerRepository.findByHtmlUrl(customGitHubData.getOwner().getHtml_url()) == null){
                        repoOwnerRepository.save(customGitHubData.getOwner());
                    }

                    saveGitRepo(customGitHubData);

                    return customGitHubData;

                } else {
                    // Handle error cases, e.g., print an error message
//
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
