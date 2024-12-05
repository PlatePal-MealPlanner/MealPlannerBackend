package com.g1appdev.mealplanner.authenticator;  

public class AuthenticationResponse {  
    private String token;  
    private String role;  
    private Long userId; // Add userId field  

    public AuthenticationResponse(String token, String role, Long userId) {  
        this.token = token;  
        this.role = role;  
        this.userId = userId; // Initialize userId  
    }  

    public String getToken() {  
        return token;  
    }  

    public void setToken(String token) {  
        this.token = token;  
    }  

    public String getRole() {  
        return role;  
    }  

    public void setRole(String role) {  
        this.role = role;  
    }  

    public Long getUserId() { // Getter for userId  
        return userId;  
    }  

    public void setUserId(Long userId) { // Setter for userId  
        this.userId = userId;  
    }  
}