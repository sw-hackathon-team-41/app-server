package com.hackathon.herb.service;

import com.hackathon.herb.dto.article.ArticleCreationDto;
import com.hackathon.herb.dto.article.ArticleDeletionDto;
import com.hackathon.herb.dto.article.ArticleUpdateDto;
import com.hackathon.herb.entity.ArticleEntity;
import com.hackathon.herb.repository.ArticleRepository;
import com.hackathon.herb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public Long createArticle(ArticleCreationDto.Req req) {
        String uId = req.getUserId();

        if (!userRepository.existsById(uId)) {
            throw new IllegalArgumentException("존재하지 않는 유저");
        }

        ArticleEntity entity = req.toEntity();
        articleRepository.save(entity);

        return 1L;
    }

    public Long uploadImage(String uId, String articleId, MultipartFile file) {
        if (!userRepository.existsById(uId)) {
            throw new IllegalArgumentException("존재하지 않는 유저");
        }

        ArticleEntity article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글"));

        article.updateThumbnail(file);
        return 1L;
    }

    public Long deleteArticle(ArticleDeletionDto.Req dto) {
        if (!userRepository.existsById(dto.getUId())) {
            throw new IllegalArgumentException("존재하지 않는 유저");
        }

        if (!articleRepository.existsById(dto.getArticleId())) {
            throw new IllegalArgumentException("존재하지 않는 게시글");
        }

        articleRepository.deleteById(dto.getArticleId());
        return 1L;
    }

    public Long updateTitle(ArticleUpdateDto.titleReq dto) {
        if (!userRepository.existsById(dto.getUId())) {
            throw new IllegalArgumentException("존재하지 않는 유저");
        }

        ArticleEntity article = articleRepository.findById(dto.getArticleId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글"));

        article.updateTitle(dto.getTitle());
        return 1L;
    }

    public Long updateContent(ArticleUpdateDto.contentReq dto) {
        if (!userRepository.existsById(dto.getUId())) {
            throw new IllegalArgumentException("존재하지 않는 유저");
        }

        ArticleEntity article = articleRepository.findById(dto.getArticleId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글"));

        article.updateContent(dto.getContent());
        return 1L;
    }
}
