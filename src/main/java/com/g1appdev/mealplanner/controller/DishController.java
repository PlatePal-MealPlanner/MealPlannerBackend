package com.g1appdev.mealplanner.controller;

import com.g1appdev.mealplanner.entity.Dish;
import com.g1appdev.mealplanner.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    // Get all dishes
    @GetMapping
    public List<Dish> getAllDishes() {
        return dishService.getAllDishes();
    }

    // Get a dish by ID
    @GetMapping("/{id}")
    public Dish getDishById(@PathVariable int id) { // Changed from Long to int
        return dishService.getDishById(id);
    }

    // Create a new dish
    @PostMapping
    public Dish createDish(@RequestBody Dish dish) {
        return dishService.createDish(dish);
    }

    // Update an existing dish
    @PutMapping("/{id}")
    public Dish updateDish(@PathVariable int id, @RequestBody Dish dishDetails) { // Changed from Long to int
        return dishService.updateDish(id, dishDetails);
    }

    // Delete a dish by ID
    @DeleteMapping("/{id}")
    public void deleteDish(@PathVariable int id) { // Changed from Long to int
        dishService.deleteDish(id);
    }

    // Add a dish with ResponseEntity
    @PostMapping("/add")
    public ResponseEntity<String> addDish(@RequestBody Dish dish) {
        dishService.createDish(dish); // Assuming this method saves the dish
        return ResponseEntity.ok("Dish added successfully");
    }
}
