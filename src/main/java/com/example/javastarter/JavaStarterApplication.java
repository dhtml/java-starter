package com.example.javastarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Java Starter Spring Boot application.
 *
 * <p>Run this class to start the embedded web server (Tomcat) on port 8080.
 * Once started, visit http://localhost:8080 in your browser or use a tool
 * like curl to explore the available REST endpoints.
 */
@SpringBootApplication
public class JavaStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaStarterApplication.class, args);
    }
}
