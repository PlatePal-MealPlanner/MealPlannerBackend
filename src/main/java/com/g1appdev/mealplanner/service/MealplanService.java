package com.g1appdev.mealplanner.service;  

import java.util.List;
import java.util.Optional;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;  

import com.g1appdev.mealplanner.entity.MealplanEntity;
import com.g1appdev.mealplanner.entity.UserEntity;
import com.g1appdev.mealplanner.repository.MealplanRepository;
import com.g1appdev.mealplanner.repository.UserRepository;  

@Service  
public class MealplanService {  

    @Autowired  
    private MealplanRepository mealplanRepository;  

    @Autowired  
    private UserRepository userRepository;  

    public List<MealplanEntity> getAllMealPlans() {  
        return mealplanRepository.findAll();  
    }  

    public Optional<MealplanEntity> getMealPlanById(Long id) {  
        return mealplanRepository.findById(id);  
    }  

    public MealplanEntity createMealPlan(MealplanEntity mealplan) {  
         
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