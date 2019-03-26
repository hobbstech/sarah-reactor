package io.github.hobbstech.weather_management.integration.open_weather_map.current.dto;

import lombok.Data;

@Data
public class WeatherItem {

    private String icon;

    private String description;

    private String main;

    private Long id;
}