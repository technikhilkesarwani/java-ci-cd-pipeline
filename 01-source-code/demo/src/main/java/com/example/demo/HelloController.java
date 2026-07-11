package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of(
                "message", "Hello from the CI/CD demo pipeline!",
                "status", "UP"
        );
    }

    @GetMapping("/api/version")
    public Map<String, String> version() {
        return Map.of("version", "1.0.0");
    }

}