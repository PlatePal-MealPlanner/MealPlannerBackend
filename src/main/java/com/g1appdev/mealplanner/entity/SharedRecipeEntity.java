package com.g1appdev.mealplanner.entity;  

import java.time.LocalDateTime;
  
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity  
@Table(name = "shared_recipe")  
public class SharedRecipeEntity {  

    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long sharedRecipeId;  

    @ManyToOne  
    @JoinColumn(name = "user_id", nullable = false)  
    private UserEntity user;  

    @ManyToOne  
    @JoinColumn(name = "recipe_id", nullable = false)  
    private RecipeEntity recipe;  

    @Column(name = "shared_with_email")  
    private String sharedWithEmail;  

    @Column(name = "created_at", nullable = false, updatable = false)  
    private final LocalDateTime createdAt;  

   
    public SharedRecipeEntity() {  
        this.createdAt = LocalDateTime.now();
    }  

    public Long getSharedRecipeId() {  
        return sharedRecipeId;  
    }  

    public void setSharedRecipeId(Long sharedRecipeId) {  
        this.sharedRecipeId = sharedRecipeId;  
    }  

    public UserEntity getUser() {  
        return user;  
    }  

    public void setUser(UserEntity user) {  
        this.user = user;  
    }  

    public RecipeEntity getRecipe() {  
        return recipe;  
    }  

    public void setRecipe(RecipeEntity recipe) {  
        this.recipe = recipe;  
    }  

    public String getSharedWithEmail() {  
        return sharedWithEmail;  
    }  

    public void setSharedWithEmail(String sharedWithEmail) {  
        this.sharedWithEmail = sharedWithEmail;  
    }  

    public LocalDateTime getCreatedAt() {  
        return createdAt;  
    }  
}