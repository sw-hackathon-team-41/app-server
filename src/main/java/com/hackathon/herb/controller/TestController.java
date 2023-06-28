package com.hackathon.herb.controller;

import com.hackathon.herb.dto.WeatherInfo;
import com.hackathon.herb.parser.WeatherAPIParser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "test")
public class TestController {
    private final WeatherAPIParser parser;

    @GetMapping
    public ResponseEntity<WeatherInfo> test() {
        WeatherInfo dto = parser.getCurrentWeather();
        return ResponseEntity.ok(dto);
    }
}
