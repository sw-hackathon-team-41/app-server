package com.hackathon.herb.dto.article;

import com.hackathon.herb.dto.user.UserInfo;
import com.hackathon.herb.entity.ArticleEntity;
import com.hackathon.herb.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Builder
@Getter @Setter
@AllArgsConstructor
public class ArticleInfo {
    private Long articleId;
    private String title;
    private String content;
    private byte[] thumbnail;
    private Long likeCnt;
    private LocalDateTime createdAt;
    private UserInfo writer;

    public static ArticleInfo of(UserEntity user, ArticleEntity article) {
        return ArticleInfo.builder()
                .articleId(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .thumbnail(article.getThumbnail())
                .likeCnt(article.getLikeCnt())
                .createdAt(article.getCreatedAt())
                .writer(UserInfo.of(user))
                .build();
    }
}
