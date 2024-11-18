package com.g1appdev.mealplanner.service;

import com.g1appdev.mealplanner.dto.PasswordChangeDTO;
import com.g1appdev.mealplanner.dto.UserProfileDTO;
import com.g1appdev.mealplanner.entity.UserEntity;
import com.g1appdev.mealplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void updateUserProfile(int userId, UserProfileDTO userProfileDTO) {
        // Fetch the user from the database
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        // Update user details
        user.setFName(userProfileDTO.getFname());
        user.setLName(userProfileDTO.getLname());
        user.setEmail(userProfileDTO.getEmail());

        // Save the updated user entity to the database
        userRepository.save(user);
    }

    public void changeUserPassword(int userId, PasswordChangeDTO passwordChangeDTO) {
        // Fetch the user from the database
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        // Validate current password
        if (!passwordEncoder.matches(passwordChangeDTO.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect.");
        }

        // Set new password
        user.setPassword(passwordEncoder.encode(passwordChangeDTO.getNewPassword()));

        // Save the updated user entity to the database
        userRepository.save(user);
    }
}
