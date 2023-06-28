package com.hackathon.herb.dto.article;

import com.hackathon.herb.entity.ArticleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ArticleCreationDto {
    @Getter @Setter @ToString
    @AllArgsConstructor
    public static class Req {
        private String userId;
        private String title;
        private String content;
        private MultipartFile photo;

        public ArticleEntity toEntity() {
            try {
                return ArticleEntity.builder()
                        .title(title)
                        .content(content)
                        .thumbnail(photo.getBytes())
                        .writer(userId)
                        .likes(0)
                        .build();
            } catch (IOException e) {
                throw new IllegalStateException("MultipartFile -> byte[] 변환 에러");
            }
        }
    }
}

