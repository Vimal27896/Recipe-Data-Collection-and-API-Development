package com.example.JsonParsing.repository;
import com.example.JsonParsing.entity.RecipeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer> {
    List<RecipeEntity> findAllByOrderByRatingDesc(Pageable pageable);
}
