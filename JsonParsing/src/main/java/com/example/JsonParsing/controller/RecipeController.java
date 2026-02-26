package com.example.JsonParsing.controller;

import com.example.JsonParsing.dto.RecipeDto;
import com.example.JsonParsing.entity.RecipeEntity;
import com.example.JsonParsing.service.ApiService;
import com.example.JsonParsing.service.ImportService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final ApiService apiService;
    private final ImportService importService;

    public RecipeController(ApiService apiService,
                            ImportService importService) {
        this.apiService = apiService;
        this.importService = importService;
    }

    @GetMapping("/import")
    public String importData() {
        try {
            return importService.importFromFile();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error during import: " + e.getMessage();
        }
    }

    @PostMapping
    public ResponseEntity<?> createRecipe(
            @Valid @RequestBody RecipeDto dto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errors);
        }
        RecipeEntity saved = apiService.createRecipe(dto);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/top")
    public ResponseEntity<Map<String, List<RecipeEntity>>> getTopRecipes(
            @RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(apiService.getTopRecipes(limit));
    }
}
