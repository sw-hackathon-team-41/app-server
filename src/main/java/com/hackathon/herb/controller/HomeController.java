package com.hackathon.herb.controller;

import com.hackathon.herb.dto.WeatherInfo;
import com.hackathon.herb.dto.article.ArticleInfo;
import com.hackathon.herb.parser.WeatherAPIParser;
import com.hackathon.herb.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class HomeController {
    private final WeatherAPIParser weatherAPIParser;
    private final ArticleService articleService;

    @GetMapping("/weather")
    public WeatherInfo getWeatherInfo() {
        return weatherAPIParser.getCurrentWeather();
    }
/*
    //인기게시물 조회
    @GetMapping("/article/bestList")
    public List<ArticleInfo> getBestArticleList(Long userId, @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return articleService.getBestArticleList(userId, pageable);
    }
*/
    @GetMapping("/article/list")
    public List<ArticleInfo> getArticleList(Long userId, @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return articleService.getArticleList(userId, pageable);
    }

    //게시글 조회
    @GetMapping("/article/{id}")
    public ResponseEntity<ArticleInfo> getArticle(@PathVariable("id") Long articleId) {
        return ResponseEntity.ok(articleService.getArticle(articleId));
    }
}
