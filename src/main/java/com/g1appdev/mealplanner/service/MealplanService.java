package com.g1appdev.mealplanner.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g1appdev.mealplanner.entity.MealplanEntity;
import com.g1appdev.mealplanner.entity.UserEntity;
import com.g1appdev.mealplanner.entity.Dish;
import com.g1appdev.mealplanner.repository.MealplanRepository;
import com.g1appdev.mealplanner.repository.UserRepository;
import com.g1appdev.mealplanner.repository.DishRepository;

@Service
public class MealplanService {

    @Autowired
    private MealplanRepository mealplanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DishRepository dishRepository;

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

    public MealplanEntity createMealPlanWithDish(MealplanEntity mealplan) {
        Optional<Dish> dishOptional = dishRepository.findById(mealplan.getDish().getId());
        if (dishOptional.isPresent()) {
            mealplan.setDish(dishOptional.get());
            mealplan.setMealDate(mealplan.getMealDate() != null ? mealplan.getMealDate() : LocalDateTime.now());
            return mealplanRepository.save(mealplan);
        } else {
            throw new RuntimeException("Dish not found");
        }
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
