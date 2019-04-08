package io.github.hobbstech.weather_management.integration.open_weather_map.current.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OpenWeatherMapCurrentForecastWeatherDTO {

    @JsonProperty("city")
    private City city;

    @JsonProperty("cnt")
    private Integer cnt;

    @JsonProperty("cod")
    private String cod;

    @JsonProperty("message")
    private Double message;

    @JsonProperty("list")
    private List<OpenWeatherMapCurrentWeatherDTO> list;

}