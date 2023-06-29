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
    public ResponseEntity<Long> createArticle(
            @RequestParam("userId") Long userId,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("herbType") String herbType,
            @RequestParam("articleType") String articleType,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        ArticleCreationDto.Req req = new ArticleCreationDto.Req(userId, title, content, herbType, articleType, file);
        return ResponseEntity.ok(articleService.createArticle(req));
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

    @PostMapping("/like")
    public ResponseEntity<Long> toggleArticleLike(@RequestBody ArticleUpdateDto.likeReq dto) {
        return ResponseEntity.ok(articleService.toggleArticleLike(dto));
    }
}
