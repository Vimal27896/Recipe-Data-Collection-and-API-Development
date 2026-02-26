package com.example.JsonParsing.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
public class RecipeDto {
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Cuisine is required")
    private String cuisine;
    private Float rating;
    @NotNull(message = "Preparation time is required")
    private Integer prep_time;
    @NotNull(message = "Cooking time is required")
    private Integer cook_time;
    private Integer total_time;
    private String description;
    private Map<String, String> nutrients;
    private String serves;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCuisine() {
        return cuisine;
    }
    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
    public Float getRating() {
        return rating;
    }
    public void setRating(Float rating) {
        this.rating = rating;
    }
    public Integer getPrep_time() {
        return prep_time;
    }
    public void setPrep_time(Integer prep_time) {
        this.prep_time = prep_time;
    }
    public Integer getCook_time() {
        return cook_time;
    }
    public void setCook_time(Integer cook_time) {
        this.cook_time = cook_time;
    }
    public Integer getTotal_time() {
        return total_time;
    }
    public void setTotal_time(Integer total_time) {
        this.total_time = total_time;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Map<String, String> getNutrients() {
        return nutrients;
    }
    public void setNutrients(Map<String, String> nutrients) {
        this.nutrients = nutrients;
    }
    public String getServes() {
        return serves;
    }
    public void setServes(String serves) {
        this.serves = serves;
    }

}
