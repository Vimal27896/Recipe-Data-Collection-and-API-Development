package com.example.JsonParsing.entity;
import jakarta.persistence.*;

@Entity
@Table(name="recipes")
public class RecipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String cuisine;
    private Float rating;
    private Integer prep_time;
    private Integer cook_time;
    private Integer total_time;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "JSON")
    private String nutrients;
    private String serves;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
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
    public int getPrep_time() {
        return prep_time;
    }
    public void setPrep_time(Integer prep_time) {
        this.prep_time = prep_time;
    }
    public int getCook_time() {
        return cook_time;
    }
    public void setCook_time(Integer cook_time) {
        this.cook_time = cook_time;
    }
    public int getTotal_time() {
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
    public String getNutrients() {
        return nutrients;
    }
    public void setNutrients(String nutrients) {
        this.nutrients = nutrients;
    }
    public String getServes() {
        return serves;
    }
    public void setServes(String serves) {
        this.serves = serves;
    }


}
