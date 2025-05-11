package com.blog.ai.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BlogController {

    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Value("${api.key}") // Use a property from application.properties
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/generateBlog")
    public Map<String, String> generateBlog(@RequestBody Map<String, String> request) {
        String title = request.get("title");
        logger.info("Received request to generate blog with title: {}", title);

        String aiApiUrl = "https://api.example.com/generate"; // Replace with actual API URL
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        Map<String, String> aiRequest = new HashMap<>();
        aiRequest.put("prompt", "Generate a blog about: " + title);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(aiRequest, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(aiApiUrl, HttpMethod.POST, entity, Map.class);
            String content = (String) response.getBody().get("content"); // Adjust based on actual API response

            Map<String, String> result = new HashMap<>();
            result.put("content", content != null ? content : "This is a sample AI-generated blog for: " + title);
            return result;
        } catch (Exception e) {
            logger.error("Error while generating blog: {}", e.getMessage());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to generate blog. Please try again later.");
            return errorResponse;
        }
    }

    @PostMapping("/publishBlog")
    public Map<String, String> publishBlog(@RequestBody Map<String, String> blogData) {
        String title = blogData.get("title");
        String content = blogData.get("content");

        logger.info("Publishing blog with title: {}", title);
        logger.debug("Blog content: {}", content);

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        return response;
    }
}