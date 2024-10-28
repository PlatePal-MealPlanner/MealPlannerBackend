package com.g1appdev.mealplanner.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbluser")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String fname;
    private String lname;
    private String email;
    private String pass;
    private String allergies;
    private String dietaryPref;
    private String intolerance;
    private String favCuisine;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Role role;

    public UserEntity() {
        super();
    }

    public UserEntity(int userId, String fname, String lname, String email, String password, String allergies,
            String dietaryPref, String intolerance, String favCuisine) {
        super();
        this.userId = userId;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.pass = password;
        this.allergies = allergies;
        this.dietaryPref = dietaryPref;
        this.intolerance = intolerance;
        this.favCuisine = favCuisine;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFName() {
        return fname;
    }

    public void setFName(String fname) {
        this.fname = fname;
    }

    public String getLName() {
        return lname;
    }

    public void setLName(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return pass;
    }

    public void setPassword(String password) {
        this.pass = password;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getDietaryPref() {
        return dietaryPref;
    }

    public void setDietaryPref(String dietaryPref) {
        this.dietaryPref = dietaryPref;
    }

    public String getIntolerance() {
        return intolerance;
    }

    public void setIntolerance(String intolerance) {
        this.intolerance = intolerance;
    }

    public String getFavCuisine() {
        return favCuisine;
    }

    public void setFavCuisine(String favCuisine) {
        this.favCuisine = favCuisine;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
