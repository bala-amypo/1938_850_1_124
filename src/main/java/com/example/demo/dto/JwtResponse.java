package com.example.demo.dto;

import java.io.Serializable;

/**
 * JwtResponse DTO: Used to send authentication data back to the client.
 */
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String token;
    private Long userId;
    private String email;
    private String role;

    // 1. Default constructor (Required by JSON libraries like Jackson)
    public JwtResponse() {
    }

    // 2. Parameterized constructor
    public JwtResponse(String token, Long userId, String email, String role) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.role = role;
    }

    // 3. Getters and Setters (Required for JSON serialization)
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    // 4. toString method (Helps you see data in your console/logs)
    @Override
    public String toString() {
        return "JwtResponse{" +
                "token='" + token + '\'' +
                ", userId=" + userId +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}