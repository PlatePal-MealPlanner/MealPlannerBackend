package com.g1appdev.mealplanner.controller;  

import com.g1appdev.mealplanner.entity.MealplanEntity;  
import com.g1appdev.mealplanner.service.MealplanService;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.http.HttpStatus;  
import org.springframework.http.ResponseEntity;  
import org.springframework.validation.annotation.Validated;  
import org.springframework.web.bind.annotation.*;  

import java.util.List;  
import java.util.Optional;  

@RestController  
@RequestMapping("/api/meal-plans")  
public class MealplanController {  

    @Autowired  
    private MealplanService mealplanService;  

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

    @PostMapping  
    public ResponseEntity<MealplanEntity> createMealPlan(@Validated @RequestBody MealplanEntity mealplan) {  
        MealplanEntity createdMealPlan = mealplanService.createMealPlan(mealplan);  
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMealPlan);  
    }  

    @PutMapping("/{id}")  
    public ResponseEntity<MealplanEntity> updateMealPlan(@PathVariable Long id, @Validated @RequestBody MealplanEntity mealplanDetails) {  
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