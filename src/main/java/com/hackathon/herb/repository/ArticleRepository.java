package com.hackathon.herb.repository;

import com.hackathon.herb.entity.ArticleEntity;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, String> {
    Optional<ArticleEntity> findById(Id id);
    Boolean existsById(Id id);
    Optional<ArticleEntity> findByIdAndWriter(Id id, String writer);
}