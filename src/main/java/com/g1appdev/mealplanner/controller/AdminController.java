package com.g1appdev.mealplanner.controller;

import com.g1appdev.mealplanner.dto.UserProfileDTO;
import com.g1appdev.mealplanner.entity.UserEntity;
import com.g1appdev.mealplanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AdminController {

    @Autowired
    private UserService userService; // Inject UserService

    // Get all users
    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userService.getAllUsers(); // Use UserService to fetch all users
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    // Get a user by ID
    @GetMapping("/users/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable long id) {
        UserEntity user = userService.getUserById(id); // Use UserService to fetch user by ID
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    // Update user
    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUserProfile(@PathVariable("id") long userId,
            @RequestBody UserProfileDTO userProfileDTO) {
        try {
            // Call the service to update the user profile
            userService.updateUserProfile(userId, userProfileDTO);
            return ResponseEntity.ok("User updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update user: " + e.getMessage());
        }
    }

    // Delete user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        if (userService.deleteUser(id)) { // Use UserService to delete user
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
