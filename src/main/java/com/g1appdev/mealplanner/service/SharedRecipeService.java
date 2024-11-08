package com.g1appdev.mealplanner.service;  

import java.util.List;  

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;

import com.g1appdev.mealplanner.entity.RecipeEntity;
import com.g1appdev.mealplanner.entity.SharedRecipeEntity;
import com.g1appdev.mealplanner.entity.UserEntity;
import com.g1appdev.mealplanner.repository.RecipeRepository;
import com.g1appdev.mealplanner.repository.SharedRecipeRepository;
import com.g1appdev.mealplanner.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;  

@Service  
public class SharedRecipeService {  

    @Autowired  
    private SharedRecipeRepository sharedRecipeRepository;  

    @Autowired  
    private UserRepository userRepository;  

    @Autowired  
    private RecipeRepository recipeRepository;  

    public List<SharedRecipeEntity> getAllSharedRecipes() {  
        return sharedRecipeRepository.findAll();  
    }  

    public SharedRecipeEntity getSharedRecipeById(Long id) {  
        return sharedRecipeRepository.findById(id)  
                .orElseThrow(() -> new EntityNotFoundException("Shared Recipe not found"));  
    }  

    public SharedRecipeEntity createSharedRecipe(SharedRecipeEntity sharedRecipe) {  
        UserEntity user = userRepository.findById(sharedRecipe.getUser().getUserId())  
                .orElseThrow(() -> new EntityNotFoundException("User not found"));  
        RecipeEntity recipe = recipeRepository.findById(sharedRecipe.getRecipe().getRecipeId())  
                .orElseThrow(() -> new EntityNotFoundException("Recipe not found"));  
        
        sharedRecipe.setUser(user);  
        sharedRecipe.setRecipe(recipe);  
        
        return sharedRecipeRepository.save(sharedRecipe);  
    }  

    public SharedRecipeEntity updateSharedRecipe(Long id, SharedRecipeEntity sharedRecipeDetails) {  
        SharedRecipeEntity sharedRecipe = sharedRecipeRepository.findById(id)  
                .orElseThrow(() -> new EntityNotFoundException("Shared Recipe not found"));  

        UserEntity user = userRepository.findById(sharedRecipeDetails.getUser().getUserId())  
                .orElseThrow(() -> new EntityNotFoundException("User not found"));  
        RecipeEntity recipe = recipeRepository.findById(sharedRecipeDetails.getRecipe().getRecipeId())  
                .orElseThrow(() -> new EntityNotFoundException("Recipe not found"));  

        sharedRecipe.setUser(user);  
        sharedRecipe.setRecipe(recipe);  
        sharedRecipe.setSharedWithEmail(sharedRecipeDetails.getSharedWithEmail());  
        
        return sharedRecipeRepository.save(sharedRecipe);  
    }  

    public void deleteSharedRecipe(Long id) {  
        if (!sharedRecipeRepository.existsById(id)) {  
            throw new EntityNotFoundException("Shared Recipe not found");  
        }  
        sharedRecipeRepository.deleteById(id);  
    }  
}