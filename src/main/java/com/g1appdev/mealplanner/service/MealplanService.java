package com.g1appdev.mealplanner.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g1appdev.mealplanner.entity.MealplanEntity;
import com.g1appdev.mealplanner.entity.RecipeEntity;
import com.g1appdev.mealplanner.entity.UserEntity;
import com.g1appdev.mealplanner.repository.MealplanRepository;
import com.g1appdev.mealplanner.repository.RecipeRepository;
import com.g1appdev.mealplanner.repository.UserRepository;

@Service
public class MealplanService {

    @Autowired
    private MealplanRepository mealplanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    public List<MealplanEntity> getMealPlansByUser(Long userId) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found with ID: " + userId);
        }
        return mealplanRepository.findByUser(userOptional.get());
    }

    public List<MealplanEntity> getAllMealPlans() {
        List<MealplanEntity> mealPlans = mealplanRepository.findAll();
        for (MealplanEntity mealPlan : mealPlans) {
            if (mealPlan.getRecipe() == null) {
                System.err.println("Error: Meal plan with ID " + mealPlan.getMealPlanId() + " has no recipe.");
            }
        }
        return mealPlans;
    }

    public Optional<MealplanEntity> getMealPlanById(Long id) {
        return mealplanRepository.findById(id);
    }

    public MealplanEntity createMealPlan(MealplanEntity mealplan) {
        Long userId = mealplan.getUser().getUserId();
        Integer recipeId = mealplan.getRecipe().getRecipeId();

        // Validate User
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Validate Recipe
        RecipeEntity recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found with ID: " + recipeId));

        mealplan.setUser(user);
        mealplan.setRecipe(recipe);
        return mealplanRepository.save(mealplan);
    }

    public MealplanEntity createMealPlanWithRecipe(MealplanEntity mealPlan) {
        RecipeEntity recipe = recipeRepository.findById(mealPlan.getRecipe().getRecipeId())
                .orElseThrow(() -> new IllegalArgumentException("Recipe not found"));

        mealPlan.setRecipe(recipe);

        // Set meal date if not provided
        if (mealPlan.getMealDate() == null) {
            mealPlan.setMealDate(LocalDateTime.now());
        }

        return mealplanRepository.save(mealPlan);
    }

    public MealplanEntity updateMealPlan(Long id, MealplanEntity mealplanDetails) {
        MealplanEntity mealplan = mealplanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MealPlan not found"));

        Optional<UserEntity> userOptional = userRepository.findById(mealplanDetails.getUser().getUserId());
        if (userOptional.isPresent()) {
            mealplan.setUser(userOptional.get());
            mealplan.setMealDate(mealplanDetails.getMealDate());
            return mealplanRepository.save(mealplan);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public void deleteMealPlan(Long id) {
        mealplanRepository.deleteById(id);
    }
}
