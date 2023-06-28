package com.hackathon.herb.controller;

import com.hackathon.herb.dto.ArticleDto;
import com.hackathon.herb.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("article")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<Long> createArticle(ArticleDto.Req req) {
        return ResponseEntity.ok(articleService.createArticle(req));
    }

    @PostMapping("/image-upload")
    public ResponseEntity<Long> uploadImage(
            @RequestParam("userId") String uId,
            @RequestParam("articleId") String articleId,
            @RequestParam("thumbnail") MultipartFile file
    ) {
        return ResponseEntity.ok(articleService.uploadImage(uId, articleId, file));
    }
}
