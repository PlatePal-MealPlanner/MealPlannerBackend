package com.g1appdev.mealplanner.entity;  

import java.time.LocalDateTime;
  
import jakarta.persistence.Column;
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
@Table(name = "tblshoppinglist")  
public class ShoppingListEntity {  

    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long shoppingListId;  

    @ManyToOne  
    @JoinColumn(name = "user_id", nullable = false)  
    private UserEntity user;  

    @Column(name = "created_at", updatable = false)  
    private LocalDateTime createdAt;  

    @Column(name = "updated_at")  
    private LocalDateTime updatedAt;  

    public ShoppingListEntity() {  
      
    }  

    public Long getShoppingListId() {  
        return shoppingListId;  
    }  

    public void setShoppingListId(Long shoppingListId) {  
        this.shoppingListId = shoppingListId;  
    }  

    public UserEntity getUser() {  
        return user;  
    }  

    public void setUser(UserEntity user) {  
        this.user = user;  
    }  

    public LocalDateTime getCreatedAt() {  
        return createdAt;  
    }  

    public LocalDateTime getUpdatedAt() {  
        return updatedAt;  
    }  

    @PrePersist  
    protected void onCreate() {  
        createdAt = LocalDateTime.now();  
    }  

    @PreUpdate  
    protected void onUpdate() {  
        updatedAt = LocalDateTime.now();  
    }  
}