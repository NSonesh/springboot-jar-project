package com.bajaj.jar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> body = new HashMap<>();
        body.put("status", "UP");
        body.put("timestamp", Instant.now().toString());
        return ResponseEntity.ok(body);
    }

    @GetMapping("/greet")
    public ResponseEntity<Map<String, String>> greet(String name) {
        Map<String, String> body = new HashMap<>();
        String n = (name == null || name.isBlank()) ? "there" : name.trim();
        body.put("message", "Hello, " + n + "! 👋");
        return ResponseEntity.ok(body);
    }
}
