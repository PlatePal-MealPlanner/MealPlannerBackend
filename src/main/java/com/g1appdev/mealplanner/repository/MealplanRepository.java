package com.g1appdev.mealplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
  
import com.g1appdev.mealplanner.entity.MealplanEntity;  

@Repository  
public interface MealplanRepository extends JpaRepository<MealplanEntity, Long> {  
    
}