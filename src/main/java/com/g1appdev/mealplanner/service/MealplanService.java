package com.g1appdev.mealplanner.service;  

import com.g1appdev.mealplanner.entity.MealplanEntity;  
import com.g1appdev.mealplanner.entity.UserEntity; // Import UserEntity  
import com.g1appdev.mealplanner.repository.MealplanRepository;  
import com.g1appdev.mealplanner.repository.UserRepository; // Import UserRepository  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;  

import java.util.List;  
import java.util.Optional;  

@Service  
public class MealplanService {  

    @Autowired  
    private MealplanRepository mealplanRepository;  

    @Autowired  
    private UserRepository userRepository; // Inject UserRepository  

    public List<MealplanEntity> getAllMealPlans() {  
        return mealplanRepository.findAll();  
    }  

    public Optional<MealplanEntity> getMealPlanById(Long id) {  
        return mealplanRepository.findById(id);  
    }  

    public MealplanEntity createMealPlan(MealplanEntity mealplan) {  
        // Validate the user exists before saving the meal plan  
        Optional<UserEntity> userOptional = userRepository.findById(mealplan.getUser().getUserId());  
        if (userOptional.isPresent()) {  
            return mealplanRepository.save(mealplan);  
        } else {  
            throw new RuntimeException("User not found");  
        }  
    }  

    public MealplanEntity updateMealPlan(Long id, MealplanEntity mealplanDetails) {  
        MealplanEntity mealplan = mealplanRepository.findById(id)  
                .orElseThrow(() -> new RuntimeException("MealPlan not found"));  

        // Validate the user exists before updating  
        Optional<UserEntity> userOptional = userRepository.findById(mealplanDetails.getUser().getUserId());  
        if (userOptional.isPresent()) {  
            mealplan.setMealDate(mealplanDetails.getMealDate());  
            mealplan.setUser(mealplanDetails.getUser());  
            return mealplanRepository.save(mealplan);  
        } else {  
            throw new RuntimeException("User not found");  
        }  
    }  

    public void deleteMealPlan(Long id) {  
        mealplanRepository.deleteById(id);  
    }  
}