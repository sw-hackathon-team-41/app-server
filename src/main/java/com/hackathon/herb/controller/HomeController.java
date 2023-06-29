package com.hackathon.herb.controller;

import com.hackathon.herb.dto.WeatherInfo;
import com.hackathon.herb.dto.article.ArticleInfo;
import com.hackathon.herb.parser.WeatherAPIParser;
import com.hackathon.herb.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HomeController {
    private final WeatherAPIParser weatherAPIParser;
    private final ArticleService articleService;

    @GetMapping("/weather")
    public WeatherInfo getWeatherInfo() {
        return weatherAPIParser.getCurrentWeather();
    }

    @GetMapping("/article/list")
    public void getArticleList() {

    }

    @GetMapping("/article/{id}")
    public ResponseEntity<ArticleInfo> getArticle(@PathVariable("id") Long articleId) {
        return ResponseEntity.ok(articleService.getArticle(articleId));
    }
}
