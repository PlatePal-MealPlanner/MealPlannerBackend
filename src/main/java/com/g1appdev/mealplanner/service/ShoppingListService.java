package com.g1appdev.mealplanner.service;  

import java.util.List;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;  

import com.g1appdev.mealplanner.entity.ShoppingListEntity;  
import com.g1appdev.mealplanner.repository.ShoppingListRepository;  

@Service  
public class ShoppingListService {  

    @Autowired  
    private ShoppingListRepository shoppingListRepository;  

    public List<ShoppingListEntity> getAllShoppingLists() {  
        return shoppingListRepository.findAll();  
    }  

    public ShoppingListEntity getShoppingListById(Long id) {  
        return shoppingListRepository.findById(id)  
            .orElseThrow(() -> new RuntimeException("Shopping List not found with id: " + id));  
    }  

    public ShoppingListEntity createShoppingList(ShoppingListEntity shoppingList) {  
        return shoppingListRepository.save(shoppingList);  
    }  

    public ShoppingListEntity updateShoppingList(Long id, ShoppingListEntity shoppingListDetails) {  
        ShoppingListEntity shoppingList = shoppingListRepository.findById(id)  
            .orElseThrow(() -> new RuntimeException("Shopping List not found with id: " + id));  

        shoppingList.setUser(shoppingListDetails.getUser());  
        
        
        return shoppingListRepository.save(shoppingList);  
    }  

    public void deleteShoppingList(Long id) {  
        ShoppingListEntity shoppingList = shoppingListRepository.findById(id)  
            .orElseThrow(() -> new RuntimeException("Shopping List not found with id: " + id));  
        
        shoppingListRepository.delete(shoppingList);  
    }  
}