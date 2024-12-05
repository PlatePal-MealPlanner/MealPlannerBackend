package com.g1appdev.mealplanner.controller;  

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;  

import com.g1appdev.mealplanner.entity.MealplanEntity;
import com.g1appdev.mealplanner.entity.RecipeEntity;
import com.g1appdev.mealplanner.entity.UserEntity;
import com.g1appdev.mealplanner.repository.RecipeRepository;
import com.g1appdev.mealplanner.repository.UserRepository;  
import com.g1appdev.mealplanner.service.MealplanService;  

@RestController  
@RequestMapping("/api/meal-plans")  
public class MealplanController {  

    @Autowired  
    private RecipeRepository recipeRepository;  

    @Autowired  
    private MealplanService mealplanService;  

    @Autowired  
    private UserRepository userRepository;  

    @GetMapping  
    public List<MealplanEntity> getAllMealPlans() {  
        return mealplanService.getAllMealPlans();  
    }  

    @GetMapping("/{id}")  
    public ResponseEntity<MealplanEntity> getMealPlanById(@PathVariable Long id) {  
        return mealplanService.getMealPlanById(id)  
                .map(ResponseEntity::ok)  
                .orElse(ResponseEntity.notFound().build());  
    }  
    
        @PostMapping("/add")  
    public ResponseEntity<?> addRecipeToMealPlan(@RequestBody Map<String, Long> request) {  
        Long userId = request.get("userId");  
        Long recipeId = request.get("recipeId");  

        // Logging the request  
        System.out.println("Request body: " + request);  
        System.out.println("Adding recipe to meal plan. UserId: " + userId + ", RecipeId: " + recipeId);  

        if (userId == null || recipeId == null) {  
            return ResponseEntity.badRequest().body("User ID and Recipe ID must not be null");  
        }  

        try {  
            UserEntity user = userRepository.findById(userId)  
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));  
            RecipeEntity recipe = recipeRepository.findById(recipeId.intValue())  
                    .orElseThrow(() -> new IllegalArgumentException("Recipe not found"));  

            MealplanEntity mealPlan = new MealplanEntity();  
            mealPlan.setUser(user);  
            mealPlan.setRecipe(recipe);  
            mealPlan.setMealDate(LocalDateTime.now());  

            MealplanEntity createdMealPlan = mealplanService.createMealPlan(mealPlan);  
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMealPlan);  
        } catch (IllegalArgumentException e) {  
            System.err.println("Error: " + e.getMessage());  
            return ResponseEntity.badRequest().body(e.getMessage());  
        } catch (Exception e) {  
            System.err.println("An unexpected error occurred: " + e.getMessage());  
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");  
        }  
    }
      

    @PutMapping("/{id}")  
    public ResponseEntity<MealplanEntity> updateMealPlan(@PathVariable Long id,  
            @Validated @RequestBody MealplanEntity mealplanDetails) {  
        Optional<MealplanEntity> mealPlanOptional = mealplanService.getMealPlanById(id);  
        if (mealPlanOptional.isPresent()) {  
            MealplanEntity updatedMealPlan = mealplanService.updateMealPlan(id, mealplanDetails);  
            return ResponseEntity.ok(updatedMealPlan);  
        } else {  
            return ResponseEntity.notFound().build();  
        }  
    }  

    @DeleteMapping("/{id}")  
    public ResponseEntity<Void> deleteMealPlan(@PathVariable Long id) {  
        mealplanService.deleteMealPlan(id);  
        return ResponseEntity.noContent().build();  
    }  
}