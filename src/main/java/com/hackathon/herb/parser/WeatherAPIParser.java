package com.hackathon.herb.parser;

import com.hackathon.herb.dto.WeatherInfo;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import java.time.LocalDateTime;

@Slf4j
@Component
public class WeatherAPIParser {
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
                JSONObject current = (JSONObject) jsonObject.get("current");
                JSONObject condition = (JSONObject) current.get("condition");

                String curWeather = condition.get("text").toString();
                String icon = condition.get("icon").toString();

                if (icon.startsWith("//")) icon = icon.substring(2);

                LocalDateTime time = LocalDateTime.now();
                float temperature = Float.parseFloat(current.get("temp_c").toString());
                float humidity = Float.parseFloat(current.get("humidity").toString());
                float uv = Float.parseFloat(current.get("uv").toString());

                dto = WeatherInfo.builder()
                        .weather(curWeather)
                        .icon(icon)
                        .time(time)
                        .temperature(temperature)
                        .humidity(humidity)
                        .uv(uv)
                        .build();

                log.info("dto : {}", dto);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dto;
    }

    public WeatherInfo getCurrentWeather() {
        return parsing();
    }
}