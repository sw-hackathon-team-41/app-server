package com.hackathon.herb.service;

import com.hackathon.herb.dto.ArticleDto;
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

    public Long createArticle(ArticleDto.Req req) {
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
}
