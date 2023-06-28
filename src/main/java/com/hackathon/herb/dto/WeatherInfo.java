package com.hackathon.herb.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@Builder
@AllArgsConstructor
public class WeatherInfo {
    private String weather;
    private String icon;
    private LocalDateTime time;
    private float temperature;
    private float humidity;
    private float uv;
}
