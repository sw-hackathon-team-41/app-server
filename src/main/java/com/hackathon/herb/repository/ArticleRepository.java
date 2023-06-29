package com.hackathon.herb.repository;

import com.hackathon.herb.dto.ArticleType;
import com.hackathon.herb.entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    Page<ArticleEntity> findAllByArticleType(ArticleType articleType, Pageable pageable);
}
