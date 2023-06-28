package com.hackathon.herb.parser;
import com.hackathon.herb.dto.WeatherInfo;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.LocalDateTime;
import java.time.LocalTime;

/** 날씨를 4시간 간격으로 가져온다.
 - API 요청마다 무거운 로직을 접근하지 않도록 Memo
 - 두 시간 간격이 4시간 이상 차이날 때 API 호출을 통해 weather 값 갱신
 */
@Component
public class WeatherAPIParser {
    private LocalTime lastCallTime;
    private String weather;

    private String call() {
        final String url = "http://api.weatherapi.com/v1/forecast.json";
        final String key = "fb9a3d940471413a88f102715230601";

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(url);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        return WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(url)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("key", key)
                        .queryParam("q", "south%20korea")
                        .queryParam("days", 1)
                        .queryParam("aqi", "no")
                        .queryParam("alerts", "no")
                        .build()
                )
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private WeatherInfo parsing() {
        String jsonStr = this.call();
        JSONParser jsonParser = new JSONParser();
        WeatherInfo dto = null;

        try {
            Object result = jsonParser.parse(jsonStr);

            if (result instanceof JSONObject jsonObject) {
                JSONObject location = (JSONObject) jsonObject.get("location");
                JSONObject current = (JSONObject) jsonObject.get("current");
                JSONObject condition = (JSONObject) current.get("condition");

                String curWeather = condition.get("text").toString();
                String icon = condition.get("icon").toString();

                if (icon.startsWith("//")) icon = icon.substring(2);

                String region = location.get("name").toString();
                LocalDateTime time = LocalDateTime.now();
                float temperature = Float.parseFloat(current.get("temp_c").toString());

                dto = WeatherInfo.builder()
                        .weather(curWeather)
                        .icon(icon)
                        .region(region)
                        .time(time)
                        .temperature(temperature)
                        .build();

                weather = curWeather;

                System.out.println(dto);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dto;
    }

    public WeatherInfo getCurrentWeather() {
        if (lastCallTime == null) lastCallTime = LocalTime.now();
        int lastCallHour = lastCallTime.getHour();
        WeatherInfo dto = null;

        LocalTime curCallTime = LocalTime.now();
        int curCallHour = curCallTime.getHour();

        if (weather == null || curCallHour - lastCallHour >= 4) {
            dto = parsing();
            lastCallTime = curCallTime;
        }

        return dto;
    }
}