package com.example.javastarter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * A simple "Hello World" controller to demonstrate the basics of Spring MVC.
 *
 * <p>{@code @RestController} is a convenience annotation that combines
 * {@code @Controller} and {@code @ResponseBody}. Every method in this class
 * automatically serialises its return value to JSON (or plain text) and writes
 * it directly to the HTTP response body — no view template needed.
 */
@RestController
public class HelloController {

    /**
     * GET /hello
     *
     * <p>Returns a friendly greeting as a JSON object.
     *
     * <p>Example:
     * <pre>
     *   curl http://localhost:8080/hello
     *   {"message":"Hello, World!"}
     * </pre>
     */
    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of("message", "Hello, World!");
    }
}
