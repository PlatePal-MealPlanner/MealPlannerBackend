package com.g1appdev.mealplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.g1appdev.mealplanner.repository.UserRepository;
import com.g1appdev.mealplanner.repository.RecipeRepository;
import com.g1appdev.mealplanner.entity.RecipeEntity;
import com.g1appdev.mealplanner.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    // Test admin access
    @GetMapping("/test")
    public ResponseEntity<String> testAdminAccess() {
        return ResponseEntity.ok("Admin Access Granted");
    }

    // View all users
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    // Update a user
    @PutMapping("/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Integer id, @RequestBody UserEntity updatedUser) {
        Optional<UserEntity> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();

            // Update user fields
            user.setFName(updatedUser.getFName());
            user.setLName(updatedUser.getLName());
            user.setEmail(updatedUser.getEmail());
            user.setRole(updatedUser.getRole());

            // Save updated user
            userRepository.save(user);

            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a user
    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Add a new recipe
    @PostMapping("/recipes")
    public ResponseEntity<RecipeEntity> addRecipe(@RequestBody RecipeEntity recipe) {
        RecipeEntity savedRecipe = recipeRepository.save(recipe);
        return ResponseEntity.ok(savedRecipe);
    }

    // Delete a recipe
    @DeleteMapping("/recipes/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable int id) {
        Optional<RecipeEntity> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            recipeRepository.deleteById(id);
            return ResponseEntity.ok("Recipe deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
