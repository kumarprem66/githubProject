package com.placement.GithubProject;

import com.placement.GithubProject.entity.MyGit;
import com.placement.GithubProject.utility.GitHubDataRequest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class GitHubDataClient {

    public static void main(String[] args) {



        String authToken = "";

        // Create HTTP headers with the Authorization header containing the token
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create a HttpEntity with the headers
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Define the URL of your Spring Boot application's endpoint
        String apiUrl = "http://localhost:8081/api/github"; // Update with your actual URL

        // Define the URL of the GitHub API endpoint for the repository
        String gitUrl = "https://api.github.com/repos/kumarprem66/FoodCorner";

        // Send a GET request with the HttpEntity containing the headers

        // Send a POST request and receive the response

        ResponseEntity<MyGit> responseEntity = restTemplate.exchange(
                gitUrl, HttpMethod.GET, entity, MyGit.class);

        // Check the response status code
        HttpStatus statusCode = (HttpStatus) responseEntity.getStatusCode();

        if (statusCode == HttpStatus.OK) {
            // The request was successful, and you can access the CustomGitHubData object
            MyGit customGitHubData = responseEntity.getBody();

            // Print or work with the CustomGitHubData object as needed
            assert customGitHubData != null;
            System.out.println("GitHub Repository Name: " + customGitHubData.getName());
            // Access other properties as well
        } else {
            // Handle error cases, e.g., print an error message
            System.err.println("Error: " + statusCode);
        }
    }
}
