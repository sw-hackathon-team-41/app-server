package com.hackathon.herb.service;

import com.hackathon.herb.dto.article.*;
import com.hackathon.herb.entity.ArticleEntity;
import com.hackathon.herb.entity.UserEntity;
import com.hackathon.herb.repository.ArticleRepository;
import com.hackathon.herb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public Long createArticle(ArticleCreationDto.Req req) {
        UserEntity user = userRepository.findById(req.getUserId()).orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 유저"));
        ArticleEntity article = req.toEntity();
        user.updateArticle(article);
        return articleRepository.save(article).getId();
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

    @Transactional(readOnly = true)
    public ArticleInfo getArticle(Long articleId) {
        ArticleEntity article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않음"));

        UserEntity writer = userRepository.findById(article.getWriter())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않음"));

        return ArticleInfo.of(writer, article);
    }

    @Transactional(readOnly = true)
    public List<ArticleInfo> getArticleList(Long userId, Pageable pageable) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않음"));

        List<Long> followings = user.getFollowings();
        List<ArticleInfo> infos = new ArrayList<>();

        for (Long following : followings) {
            UserEntity followUser = userRepository.findById(following)
                    .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않음"));

            List<ArticleEntity> articles = followUser.getArticles();

            for (ArticleEntity article : articles) {
                ArticleInfo of = ArticleInfo.of(followUser, article);
                infos.add(of);
            }
        }
        return infos;
    }
  
    @Transactional(readOnly = true)
    public Page<ArticlePreviewInfo> getHotArticleList(Pageable pageable) {
        return articleRepository.findAll(pageable)
                .map(ArticlePreviewInfo::of);
    } 
}
