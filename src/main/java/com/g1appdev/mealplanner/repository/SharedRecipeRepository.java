package com.g1appdev.mealplanner.repository;  

import org.springframework.data.jpa.repository.JpaRepository;

import com.g1appdev.mealplanner.entity.SharedRecipeEntity;  

public interface SharedRecipeRepository extends JpaRepository<SharedRecipeEntity, Long> {  
}