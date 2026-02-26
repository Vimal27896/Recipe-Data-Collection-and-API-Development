package com.example.JsonParsing.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.JsonParsing.dto.RecipeDto;
import com.example.JsonParsing.entity.RecipeEntity;
import com.example.JsonParsing.mapper.RecipeMapper;
import com.example.JsonParsing.repository.RecipeRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class ImportService{
    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;
    private final ObjectMapper objectMapper;
    private static final int batch_size=500;
    private static final String fpath="C:/JsonParsing/US_recipes_null.json";
    public ImportService(RecipeRepository recipeRepository,
                               RecipeMapper recipeMapper,
                               ObjectMapper objectMapper) {
        this.recipeRepository=recipeRepository;
        this.recipeMapper=recipeMapper;
        this.objectMapper=objectMapper;
    }
    @Transactional
    public String importFromFile() throws Exception {
        File file=new File(fpath);
        if (!file.exists()) {
            return "File not found:"+fpath;
        }
        System.out.println("Reading file: " + fpath);
        JsonParser parser = objectMapper.getFactory().createParser(file);
        List<RecipeEntity> batch = new ArrayList<>();
        int totalSaved = 0;
        parser.nextToken();
        while (parser.nextToken()!=JsonToken.END_OBJECT) {
            parser.nextToken();
            RecipeDto dto=objectMapper.readValue(parser,RecipeDto.class);
            if (dto == null) continue;
            RecipeEntity entity=recipeMapper.toEntity(dto);
            if (entity == null) continue;
            batch.add(entity);
            if (batch.size() >=batch_size) {
                recipeRepository.saveAll(batch);
                totalSaved += batch.size();
                System.out.println("Saved " + totalSaved + " records...");
                batch.clear();
            }
        }
        if (!batch.isEmpty()) {
            recipeRepository.saveAll(batch);
            totalSaved += batch.size();
        }
        parser.close();
        System.out.println("Total records saved:"+ totalSaved);
        return "Data parsed successfully.Total records saved:" +totalSaved;
    }
}