package com.hackathon.herb.service;

import com.hackathon.herb.dto.ArticleType;
import com.hackathon.herb.dto.HerbType;
import com.hackathon.herb.dto.article.*;
import com.hackathon.herb.entity.ArticleEntity;
import com.hackathon.herb.entity.UserEntity;
import com.hackathon.herb.repository.ArticleRepository;
import com.hackathon.herb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        UserEntity user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 유저"));

        ArticleEntity article = req.toEntity(user);
        user.updateArticle(article);
        article.updateThumbnail(req.getFile());
        article.setWriterHerbType(Enum.valueOf(HerbType.class, req.getHerbType()));
        article.setArticleType(Enum.valueOf(ArticleType.class, req.getArticleType()));

        return articleRepository.save(article).getId();
    }

    public Long deleteArticle(ArticleDeletionDto.Req dto) {
        UserEntity user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저"));

        ArticleEntity article = articleRepository.findById(dto.getArticleId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글"));

        if (!Objects.equals(user.getId(), article.getWriter().getId())) {
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

        if (!Objects.equals(user.getId(), article.getWriter().getId())) {
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

        if (!Objects.equals(user.getId(), article.getWriter().getId())) {
            return 0L;
        }

        article.updateContent(dto.getContent());
        return 1L;
    }

    @Transactional(readOnly = true)
    public ArticleInfo getArticle(Long articleId) {
        ArticleEntity article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않음"));

        UserEntity writer = userRepository.findById(article.getWriter().getId())
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
        return articleRepository.findAllByArticleType(ArticleType.NORMAL, pageable)
                .map(ArticlePreviewInfo::of);
    }

    @Transactional(readOnly = true)
    public Page<ArticlePreviewInfo> getQnaArticleList(Pageable pageable) {
        return articleRepository.findAllByArticleType(ArticleType.QNA, pageable)
                .map(ArticlePreviewInfo::of);
    }

    public Long toggleArticleLike(ArticleUpdateDto.likeReq dto) {
         Long userId = dto.getUserId();
         Long articleId = dto.getArticleId();

        ArticleEntity article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("toggleArticleLike() : 게시글을 찾을 수 없음"));

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("toggleArticleLike() : 유저를 찾을 수 없음"));

        if (article.getUsersWhoLikeThis().contains(user)) {
            article.setLikeCnt(article.getLikeCnt() - 1);
            article.getUsersWhoLikeThis().remove(user);
        } else {
            article.getUsersWhoLikeThis().add(user);
            article.setLikeCnt(article.getLikeCnt() + 1);
        }

        return articleRepository.save(article).getLikeCnt();
    }
}
