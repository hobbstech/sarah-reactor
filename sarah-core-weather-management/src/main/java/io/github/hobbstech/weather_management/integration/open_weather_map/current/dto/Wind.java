package io.github.hobbstech.weather_management.integration.open_weather_map.current.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Wind {

    @JsonProperty("deg")
    private Double deg;

    @JsonProperty("speed")
    private Double speed;
}