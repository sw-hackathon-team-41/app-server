package com.hackathon.herb.controller;

import com.hackathon.herb.dto.WeatherInfo;
import com.hackathon.herb.dto.article.ArticleInfo;
import com.hackathon.herb.dto.article.ArticlePreviewInfo;
import com.hackathon.herb.parser.WeatherAPIParser;
import com.hackathon.herb.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @GetMapping("/videoType")
    public long getVideoType() {
        WeatherInfo weatherInfo = weatherAPIParser.getCurrentWeather();
        float temperature = weatherInfo.getTemperature();
        float humidity = weatherInfo.getHumidity();
        float uv = weatherInfo.getUv();

        if (temperature >= 20) weatherInfo.setVideoType(1);
        else if (temperature >= 10) weatherInfo.setVideoType(2);
        else weatherInfo.setVideoType(5);

        if (humidity >= 23) weatherInfo.setVideoType(3);
        if (uv >= 6) weatherInfo.setVideoType(4);

        return weatherInfo.getVideoType();
    }

    @GetMapping("/article/list/{userId}")
    public List<ArticleInfo> getFriendArticleList(@PathVariable("userId") Long userId, @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return articleService.getArticleList(userId, pageable);
    }

    @GetMapping("/article/list")
    public Page<ArticlePreviewInfo> getHotArticleList(@PageableDefault(size = 10, sort = "likeCnt", direction = Sort.Direction.DESC) Pageable pageable) {
        return articleService.getHotArticleList(pageable);
    }

    @GetMapping("/article/{articleId}")
    public ResponseEntity<ArticleInfo> getArticle(@PathVariable("articleId") Long articleId) {
        return ResponseEntity.ok(articleService.getArticle(articleId));
    }

    @GetMapping("/article/list/qna")
    public Page<ArticlePreviewInfo> getQnaArticleList(@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return articleService.getQnaArticleList(pageable);
    }
}
