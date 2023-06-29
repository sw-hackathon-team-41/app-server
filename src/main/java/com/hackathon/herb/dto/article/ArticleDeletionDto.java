package com.hackathon.herb.dto.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ArticleDeletionDto {
    @Getter @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Req {
        private Long userId;
        private Long articleId;
    }
}
