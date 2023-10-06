package com.placement.GithubProject.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // Customize RestTemplate as needed
        // For example, you can set timeouts:
        SimpleClientHttpRequestFactory factory = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
        factory.setConnectTimeout(5000); // 5 seconds
        factory.setReadTimeout(5000);    // 5 seconds

        return restTemplate;
    }
}

