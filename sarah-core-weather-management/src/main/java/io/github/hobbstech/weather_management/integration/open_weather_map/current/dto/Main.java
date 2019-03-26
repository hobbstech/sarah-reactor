package io.github.hobbstech.weather_management.integration.open_weather_map.current.dto;

import lombok.Data;

@Data
public class Main {

    private Double temp;

    private Double tempMin;

    private Double grndLevel;

    private Long humidity;

    private Double pressure;

    private Double seaLevel;

    private Double tempMax;
}