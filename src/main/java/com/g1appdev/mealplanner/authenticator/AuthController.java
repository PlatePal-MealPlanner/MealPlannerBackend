package com.g1appdev.mealplanner.authenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g1appdev.mealplanner.entity.UserEntity;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthService auserve;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        System.out.println("Register request: " + request);
        return ResponseEntity.ok(auserve.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(auserve.authenticate(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UserEntity> getUserProfile(@RequestHeader("Authorization") String token) {

        System.out.println("Received token: " + token);
        String jwtToken = token.replace("Bearer ", "");
        UserEntity userProfile = auserve.getUserProfile(jwtToken);

        return ResponseEntity.ok(userProfile);
    }
}
