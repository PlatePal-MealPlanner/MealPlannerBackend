package com.g1appdev.mealplanner.repository;  

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g1appdev.mealplanner.entity.ShoppingListEntity;  

@Repository  
public interface ShoppingListRepository extends JpaRepository<ShoppingListEntity, Long> {  
    
}