package com.g1appdev.mealplanner.entity;  

import java.time.LocalDateTime;
  
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity  
@Table(name = "tblmealplan")  
public class MealplanEntity {  

    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long mealPlanId;  

    @ManyToOne  
    @JoinColumn(name = "user_id", nullable = false)  
    private UserEntity user; 

    private LocalDateTime mealDate;  

    private LocalDateTime createdAt;  

    private LocalDateTime updatedAt;  

    
    @PrePersist  
    protected void onCreate() {  
        createdAt = LocalDateTime.now();  
        updatedAt = LocalDateTime.now();  
    }  

    @PreUpdate  
    protected void onUpdate() {  
        updatedAt = LocalDateTime.now();  
    }  

   
    public Long getMealPlanId() {  
        return mealPlanId;  
    }  

    public void setMealPlanId(Long mealPlanId) {  
        this.mealPlanId = mealPlanId;  
    }  

    public UserEntity getUser() {
        return user;  
    }  

    public void setUser(UserEntity user) {  
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