package com.g1appdev.mealplanner.authenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.g1appdev.mealplanner.config.jwtService;
import com.g1appdev.mealplanner.entity.Role;
import com.g1appdev.mealplanner.entity.UserEntity;
import com.g1appdev.mealplanner.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private jwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        System.out.println("Registering user: " + request.getEmail());

        UserEntity userEntity = new UserEntity();
        userEntity.setFName(request.getFname());
        userEntity.setLName(request.getLname());
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userEntity.setRole(Role.USER);

        repository.save(userEntity);
        String jwtToken = jwtService.generateToken(userEntity);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // Authenticate the user first
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()));

            // If authentication succeeds, retrieve the user
            UserEntity user = repository.findByEmail(request.getEmail())
                    .orElseThrow(
                            () -> new UsernameNotFoundException("User not found with email: " + request.getEmail()));

            // Generate a new JWT token
            String jwtToken = jwtService.generateToken(user);
            return new AuthenticationResponse(jwtToken);
        } catch (Exception e) {
            // Handle authentication failure
            throw new RuntimeException("Invalid email or password");
        }
    }

}
