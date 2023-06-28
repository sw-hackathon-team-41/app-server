package com.hackathon.herb.service;

import com.hackathon.herb.dto.article.ArticleCreationDto;
import com.hackathon.herb.dto.article.ArticleDeletionDto;
import com.hackathon.herb.dto.article.ArticleUpdateDto;
import com.hackathon.herb.entity.ArticleEntity;
import com.hackathon.herb.entity.UserEntity;
import com.hackathon.herb.repository.ArticleRepository;
import com.hackathon.herb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public Long createArticle(ArticleCreationDto.Req req) {
        Long uId = req.getUserId();

        if (!userRepository.existsById(uId)) {
            throw new IllegalArgumentException("존재하지 않는 유저");
        }

        ArticleEntity entity = req.toEntity();
        articleRepository.save(entity);

        return 1L;
    }

    public Long uploadImage(Long uId, Long articleId, MultipartFile file) {
        UserEntity user = userRepository.findById(uId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저"));

        ArticleEntity article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글"));

        if (!Objects.equals(article.getWriter(), user.getId())) {
            return 0L;
        }

        article.updateThumbnail(file);
        return 1L;
    }

    public Long deleteArticle(ArticleDeletionDto.Req dto) {
        UserEntity user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저"));

        ArticleEntity article = articleRepository.findById(dto.getArticleId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글"));

        if (!Objects.equals(user.getId(), article.getWriter())) {
            return 0L;
        }

        articleRepository.deleteById(dto.getArticleId());
        return 1L;
    }

    public Long updateTitle(ArticleUpdateDto.titleReq dto) {
        UserEntity user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저"));

        ArticleEntity article = articleRepository.findById(dto.getArticleId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글"));

        if (!Objects.equals(user.getId(), article.getWriter())) {
            return 0L;
        }

        article.updateTitle(dto.getTitle());
        return 1L;
    }

    public Long updateContent(ArticleUpdateDto.contentReq dto) {
        UserEntity user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저"));

        ArticleEntity article = articleRepository.findById(dto.getArticleId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글"));

        if (!Objects.equals(user.getId(), article.getWriter())) {
            return 0L;
        }

        article.updateContent(dto.getContent());
        return 1L;
    }
}
