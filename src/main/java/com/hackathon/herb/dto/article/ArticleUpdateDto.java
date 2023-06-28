package com.hackathon.herb.dto.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class ArticleUpdateDto {
    @Getter @Setter
    @AllArgsConstructor
    public static class titleReq {
        private String uId;
        private String articleId;
        private String title;
    }

    @Getter @Setter
    @AllArgsConstructor
    public static class contentReq {
        private String uId;
        private String articleId;
        private String content;
    }
}
