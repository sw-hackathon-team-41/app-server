package com.hackathon.herb.dto.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ArticleUpdateDto {
    @Getter @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class titleReq {
        private Long userId;
        private Long articleId;
        private String title;
    }

    @Getter @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class contentReq {
        private Long userId;
        private Long articleId;
        private String content;
    }
}
