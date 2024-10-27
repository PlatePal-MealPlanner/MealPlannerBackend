package com.g1appdev.mealplanner.controller;

import java.util.*;
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

import com.g1appdev.mealplanner.entity.RecipeEntity;
import com.g1appdev.mealplanner.service.RecipeService;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    @Autowired
    RecipeService rserve;

    @PostMapping("/addrecipe")
    public ResponseEntity<RecipeEntity> postRecipe(@RequestBody RecipeEntity recipe) {
        try {
            RecipeEntity createdRecipe = rserve.postRecipe(recipe);
            return new ResponseEntity<>(createdRecipe, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allrecipe")
    public ResponseEntity<List<RecipeEntity>> getAllRecipes() {
        List<RecipeEntity> recipes = rserve.getAllRecipes();
        if (recipes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeEntity> getRecipeById(@PathVariable int id) {
        Optional<RecipeEntity> recipeData = rserve.getRecipeById(id);
        if (recipeData.isPresent()) {
            return new ResponseEntity<>(recipeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RecipeEntity> updateRecipe(@PathVariable int id, @RequestBody RecipeEntity newRecipeDetails) {
        try {
            RecipeEntity updatedRecipe = rserve.putRecipeDetails(id, newRecipeDetails);
            return new ResponseEntity<>(updatedRecipe, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RecipeEntity> deleteRecipeById(@PathVariable int id) {
        try {
            rserve.deleteRecipeById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cuisine/{cuisineType}")
    public ResponseEntity<List<RecipeEntity>> findByCuisineType(@PathVariable String cuisineType) {
        List<RecipeEntity> recipes = rserve.findByCuisineType(cuisineType);
        if (recipes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("/meal/{mealType}")
    public ResponseEntity<List<RecipeEntity>> findByMealType(@PathVariable String mealType) {
        List<RecipeEntity> recipes = rserve.findByMealType(mealType);
        if (recipes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("/ingredients/{ingredients}")
    public ResponseEntity<List<RecipeEntity>> findByIngredients(@PathVariable String ingredients) {
        List<RecipeEntity> recipes = rserve.findByIngredients(ingredients);
        if (recipes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
