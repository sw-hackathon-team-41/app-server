package com.hackathon.herb.controller;

import com.hackathon.herb.dto.article.ArticleCreationDto;
import com.hackathon.herb.dto.article.ArticleDeletionDto;
import com.hackathon.herb.dto.article.ArticleUpdateDto;
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
    public ResponseEntity<Long> createArticle(@RequestBody ArticleCreationDto.Req req) {
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

    @DeleteMapping
    public ResponseEntity<Long> deleteArticle(@RequestBody ArticleDeletionDto.Req dto) {
        return ResponseEntity.ok(articleService.deleteArticle(dto));
    }

    @PatchMapping("/title")
    public ResponseEntity<Long> updateArticleTitle(@RequestBody ArticleUpdateDto.titleReq dto) {
        return ResponseEntity.ok(articleService.updateTitle(dto));
    }

    @PatchMapping("/content")
    public ResponseEntity<Long> updateArticleContent(@RequestBody ArticleUpdateDto.contentReq dto) {
        return ResponseEntity.ok(articleService.updateContent(dto));
    }
}
