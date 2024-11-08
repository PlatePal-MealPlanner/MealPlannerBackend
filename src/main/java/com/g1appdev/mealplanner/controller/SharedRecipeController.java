package com.g1appdev.mealplanner.controller;  

import java.util.List;  

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g1appdev.mealplanner.entity.SharedRecipeEntity;
import com.g1appdev.mealplanner.service.SharedRecipeService;

@RestController  
@RequestMapping("/api/shared-recipes")  
public class SharedRecipeController {  

    @Autowired  
    private SharedRecipeService sharedRecipeService;  

    @GetMapping  
    public ResponseEntity<List<SharedRecipeEntity>> getAllSharedRecipes() {  
        return ResponseEntity.ok(sharedRecipeService.getAllSharedRecipes());  
    }  

    @GetMapping("/{id}")  
    public ResponseEntity<SharedRecipeEntity> getSharedRecipeById(@PathVariable Long id) {  
        return ResponseEntity.ok(sharedRecipeService.getSharedRecipeById(id));  
    }  

    @PostMapping  
    public ResponseEntity<SharedRecipeEntity> createSharedRecipe(@RequestBody SharedRecipeEntity sharedRecipe) {  
        SharedRecipeEntity createdRecipe = sharedRecipeService.createSharedRecipe(sharedRecipe);  
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecipe);  
    }  

    @PutMapping("/{id}")  
    public ResponseEntity<SharedRecipeEntity> updateSharedRecipe(  
            @PathVariable Long id, @RequestBody SharedRecipeEntity sharedRecipeDetails) {  
        SharedRecipeEntity updatedRecipe = sharedRecipeService.updateSharedRecipe(id, sharedRecipeDetails);  
        return ResponseEntity.ok(updatedRecipe);  
    }  

    @DeleteMapping("/{id}")  
    public ResponseEntity<Void> deleteSharedRecipe(@PathVariable Long id) {  
        sharedRecipeService.deleteSharedRecipe(id);  
        return ResponseEntity.noContent().build();  
    }  
}