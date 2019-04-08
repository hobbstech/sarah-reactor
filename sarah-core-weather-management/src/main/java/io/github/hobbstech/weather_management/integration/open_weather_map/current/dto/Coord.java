package io.github.hobbstech.weather_management.integration.open_weather_map.current.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Coord {

    @JsonProperty("lon")
    private Double lon;

    @JsonProperty("lat")
    private Double lat;
}