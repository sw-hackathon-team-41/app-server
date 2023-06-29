package com.hackathon.herb.dto.article;

import com.hackathon.herb.entity.ArticleEntity;
import com.hackathon.herb.entity.UserEntity;
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
        private String herbType;
        private String articleType;
        private MultipartFile file;

        public ArticleEntity toEntity(UserEntity user) {
            ArticleEntity entity = ArticleEntity.builder()
                    .title(title)
                    .content(content)
                    .writer(user)
                    .likeCnt(0)
                    .build();

            if (this.file != null) entity.updateThumbnail(file);
            if (entity.getCreatedAt() == null) entity.updateCreatedAt();

            return entity;
        }
    }
}

