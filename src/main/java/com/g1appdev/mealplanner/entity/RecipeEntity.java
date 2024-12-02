package com.g1appdev.mealplanner.entity;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblrecipe")
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeId;

    private String title;
    private String description;
    private String ingredients;
    private int prepTime;
    private String nutritionInfo;
    private String cuisineType;
    private String mealType;
    private double ratingsAverage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "recipes")
    private Set<ShoppingListItemsEntity> shoppingListItems;

    public RecipeEntity() {
        super();
    }

    public RecipeEntity(int recipeId, String title, String description, String ingredients, int prepTime,
            String nutritionInfo, String cuisineType, String mealType, double ratingsAverage) {
        super();
        this.recipeId = recipeId;
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.prepTime = prepTime;
        this.nutritionInfo = nutritionInfo;
        this.cuisineType = cuisineType;
        this.mealType = mealType;
        this.ratingsAverage = ratingsAverage;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public String getNutritionInfo() {
        return nutritionInfo;
    }

    public void setNutritionInfo(String nutritionInfo) {
        this.nutritionInfo = nutritionInfo;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public double getRatingsAverage() {
        return ratingsAverage;
    }

    public void setRatingsAverage(double ratingsAverage) {
        this.ratingsAverage = ratingsAverage;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Set<ShoppingListItemsEntity> getShoppingListItems() {
        return shoppingListItems;
    }

    public void setShoppingListItems(Set<ShoppingListItemsEntity> shoppingListItems) {
        this.shoppingListItems = shoppingListItems;
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