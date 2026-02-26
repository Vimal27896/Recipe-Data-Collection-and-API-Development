package com.example.JsonParsing.service;

import com.example.JsonParsing.dto.RecipeDto;
import com.example.JsonParsing.entity.RecipeEntity;
import com.example.JsonParsing.mapper.RecipeMapper;
import com.example.JsonParsing.repository.RecipeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;
    private final ObjectMapper objectMapper;

    public ApiService(RecipeRepository recipeRepository,
                      RecipeMapper recipeMapper,
                      ObjectMapper objectMapper) {
        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
        this.objectMapper = objectMapper;
    }
    public RecipeEntity createRecipe(RecipeDto dto) {
        if (dto.getPrep_time() != null && dto.getCook_time() != null) {
            dto.setTotal_time(dto.getPrep_time() + dto.getCook_time());
        }
        RecipeEntity entity = recipeMapper.toEntity(dto);
        return recipeRepository.save(entity);
    }
    public Map<String, List<RecipeEntity>> getTopRecipes(int limit) {
        Pageable pageable = PageRequest.of(0, limit,
                Sort.by("rating").descending());
        List<RecipeEntity> topRecipes = recipeRepository
                .findAllByOrderByRatingDesc(pageable);
        Map<String, List<RecipeEntity>> response = new HashMap<>();
        response.put("data", topRecipes);
        return response;
    }
}