package com.hackathon.herb.controller;

import com.hackathon.herb.dto.article.ArticleCreationDto;
import com.hackathon.herb.dto.article.ArticleDeletionDto;
import com.hackathon.herb.dto.article.ArticleUpdateDto;
import com.hackathon.herb.service.ArticleService;
import com.hackathon.herb.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
            @RequestParam(value = "thumbnail", required = false) MultipartFile file,
            @RequestParam("userId") Long userId,
            @RequestParam("articleId") Long articleId
    ) {
        return ResponseEntity.ok(articleService.uploadImage(userId, articleId, file));
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


    @Autowired
    private LikeService likeService;

    @PostMapping("/{userId}/like/{articleId}")
    public ResponseEntity like(@PathVariable("articleId") Long articleId, @PathVariable("userId") Long userId) {
        likeService.like(articleId, userId);
        return ResponseEntity.ok().build();

    }

    //2. 좋아요 취소
    @DeleteMapping("/{userId}/unlike/{articleId}")
    public ResponseEntity deleteLike(@PathVariable("articleId") Long articleId, @PathVariable("userId") Long userId) {
        likeService.deleteLike(articleId, userId);
        return ResponseEntity.ok().build();
    }

    //3. 좋아요 여부 확인
    @GetMapping("/{userId}/isLike/{articleId}")
    public ResponseEntity isLike(@PathVariable("articleId") Long articleId, @PathVariable("userId") Long userId) {
        boolean result = likeService.isLike(articleId, userId);
        return ResponseEntity.ok().body(result);
    }
}
