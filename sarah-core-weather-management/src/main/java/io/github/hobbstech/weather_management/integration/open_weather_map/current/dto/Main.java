package io.github.hobbstech.weather_management.integration.open_weather_map.current.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Main {

    @JsonProperty("temp")
    private Double temp;

    @JsonProperty("temp_min")
    private Double tempMin;

    @JsonProperty("grnd_level")
    private Double grndLevel;

    @JsonProperty("temp_kf")
    private Integer tempKf;

    @JsonProperty("humidity")
    private Integer humidity;

    @JsonProperty("pressure")
    private Double pressure;

    @JsonProperty("sea_level")
    private Double seaLevel;

    @JsonProperty("temp_max")
    private Double tempMax;

}