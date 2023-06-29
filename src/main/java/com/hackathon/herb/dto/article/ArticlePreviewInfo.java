package com.hackathon.herb.dto.article;

import com.hackathon.herb.entity.ArticleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter @Setter
@AllArgsConstructor
public class ArticlePreviewInfo {
    private Long articleId;
    private String title;
    private String content;
    private byte[] thumbnail;
    private Long likeCnt;
    private LocalDateTime createdAt;

    public static ArticlePreviewInfo of(ArticleEntity entity) {
        return ArticlePreviewInfo.builder()
                .articleId(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .thumbnail(entity.getThumbnail())
                .likeCnt(entity.getLikeCnt())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
