package com.g1appdev.mealplanner.controller;  

import java.util.List;  

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g1appdev.mealplanner.entity.ShoppingListEntity;
import com.g1appdev.mealplanner.service.ShoppingListService;

@RestController  
@RequestMapping("/api/shopping-lists")  
public class ShoppingListController {  

    @Autowired  
    private ShoppingListService shoppingListService;  

    @GetMapping  
    public ResponseEntity<List<ShoppingListEntity>> getAllShoppingLists() {  
        return ResponseEntity.ok(shoppingListService.getAllShoppingLists());  
    }  

    @GetMapping("/{id}")  
    public ResponseEntity<ShoppingListEntity> getShoppingListById(@PathVariable Long id) {  
        ShoppingListEntity shoppingList = shoppingListService.getShoppingListById(id);  
        return shoppingList != null ? ResponseEntity.ok(shoppingList) : ResponseEntity.notFound().build();  
    }  

    @PostMapping  
    public ResponseEntity<ShoppingListEntity> createShoppingList(@RequestBody ShoppingListEntity shoppingList) {  
        ShoppingListEntity createdShoppingList = shoppingListService.createShoppingList(shoppingList);  
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShoppingList);  
    }  

    @PutMapping("/{id}")  
    public ResponseEntity<ShoppingListEntity> updateShoppingList(@PathVariable Long id, @RequestBody ShoppingListEntity shoppingListDetails) {  
        ShoppingListEntity updatedShoppingList = shoppingListService.updateShoppingList(id, shoppingListDetails);  
        return updatedShoppingList != null ? ResponseEntity.ok(updatedShoppingList) : ResponseEntity.notFound().build();  
    }  

    @DeleteMapping("/{id}")  
    public ResponseEntity<Void> deleteShoppingList(@PathVariable Long id) {  
        shoppingListService.deleteShoppingList(id);  
        return ResponseEntity.noContent().build();  
    }  
}