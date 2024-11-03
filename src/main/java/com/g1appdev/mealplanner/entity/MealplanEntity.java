package com.g1appdev.mealplanner.entity;  

import jakarta.persistence.*;  
import java.time.LocalDateTime;  

@Entity  
@Table(name = "tblmealplan")  
public class MealplanEntity {  

    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long mealPlanId;  

    @ManyToOne  
    @JoinColumn(name = "user_id", nullable = false)  
    private UserEntity user; // Ensure this is UserEntity  

    private LocalDateTime mealDate;  

    private LocalDateTime createdAt;  

    private LocalDateTime updatedAt;  

    // Lifecycle callbacks  
    @PrePersist  
    protected void onCreate() {  
        createdAt = LocalDateTime.now();  
        updatedAt = LocalDateTime.now();  
    }  

    @PreUpdate  
    protected void onUpdate() {  
        updatedAt = LocalDateTime.now();  
    }  

    // Getters and Setters  
    public Long getMealPlanId() {  
        return mealPlanId;  
    }  

    public void setMealPlanId(Long mealPlanId) {  
        this.mealPlanId = mealPlanId;  
    }  

    public UserEntity getUser() { // Changed from User to UserEntity  
        return user;  
    }  

    public void setUser(UserEntity user) { // Changed from User to UserEntity  
        this.user = user;  
    }  

    public LocalDateTime getMealDate() {  
        return mealDate;  
    }  

    public void setMealDate(LocalDateTime mealDate) {  
        this.mealDate = mealDate;  
    }  

    public LocalDateTime getCreatedAt() {  
        return createdAt;  
    }  

    public LocalDateTime getUpdatedAt() {  
        return updatedAt;  
    }  
}