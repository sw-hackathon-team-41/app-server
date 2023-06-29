package com.hackathon.herb.repository;

import com.hackathon.herb.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
}
