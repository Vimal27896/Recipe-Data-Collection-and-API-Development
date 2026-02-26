package com.example.JsonParsing.mapper;
import com.example.JsonParsing.dto.RecipeDto;
import com.example.JsonParsing.entity.RecipeEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
@Component
public class RecipeMapper {

    private final ObjectMapper objectMapper;
    public RecipeMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    public RecipeEntity toEntity(RecipeDto dto) {
        if (dto == null) return null;
        RecipeEntity entity = new RecipeEntity();
        entity.setCuisine(dto.getCuisine());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setServes(dto.getServes());
        entity.setRating(isValidFloat(dto.getRating()) ? dto.getRating() : null);
        entity.setTotal_time(dto.getTotal_time());
        entity.setPrep_time(dto.getPrep_time());
        entity.setCook_time(dto.getCook_time());
        try{
            if (dto.getNutrients() != null) {
                entity.setNutrients(objectMapper.writeValueAsString(dto.getNutrients()));
            }
        } catch (Exception e) {
            System.out.println("Error converting list to JSON: " + e.getMessage());
        }
        return entity;
    }
    private boolean isValidFloat(Float value) {
    return value != null && !value.isNaN() && !value.isInfinite();
}

}