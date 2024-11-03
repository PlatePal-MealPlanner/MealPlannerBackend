package com.g1appdev.mealplanner.repository;

import com.g1appdev.mealplanner.entity.MealplanEntity;
import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.stereotype.Repository;  

@Repository  
public interface MealplanRepository extends JpaRepository<MealplanEntity, Long> {  
    // Additional query methods can be defined here if needed  
}