package com.hackathon.herb.dto.article;

import com.hackathon.herb.entity.ArticleEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

public class ArticleCreationDto {
    @Getter @Setter @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Req {
        private Long userId;
        private String title;
        private String content;
        private MultipartFile photo;

        public ArticleEntity toEntity() {
            ArticleEntity entity =  ArticleEntity.builder()
                    .title(title)
                    .content(content)
                    .writer(userId)
                    .likes(0)
                    .build();

            if (this.photo != null) entity.updateThumbnail(photo);
            return entity;
        }
    }
}

