package com.example.lab7.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("application", "Lab7 - Spring REST API");
        response.put("version", "1.0.0");
        response.put("status", "Running");
        response.put("java", "Java 21");
        response.put("springBoot", "Spring Boot 3.4.11");
        response.put("endpoints", Map.of(
            "books", "/api/books",
            "h2Console", "/h2-console",
            "actuator", "/actuator",
            "health", "/actuator/health"
        ));
        response.put("message", "Welcome to Lab7 Spring REST API - Upgraded to Java 21!");
        return response;
    }
}
