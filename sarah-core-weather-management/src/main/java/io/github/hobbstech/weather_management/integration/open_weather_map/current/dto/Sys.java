package io.github.hobbstech.weather_management.integration.open_weather_map.current.dto;

import lombok.Data;

@Data
public class Sys {

    private String country;

    private Long sunrise;

    private Long sunset;

    private Double message;
}