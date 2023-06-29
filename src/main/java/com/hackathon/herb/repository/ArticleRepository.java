package com.hackathon.herb.repository;

import com.hackathon.herb.entity.ArticleEntity;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<ArticleEntity, String> {
    Optional<ArticleEntity> findById(Long id);
    Boolean existsById(Long id);
    Optional<ArticleEntity> findByIdAndWriter(Long id, String writer);
}
