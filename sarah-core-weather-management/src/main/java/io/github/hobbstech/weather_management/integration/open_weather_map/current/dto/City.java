package io.github.hobbstech.weather_management.integration.open_weather_map.current.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class City {

    @JsonProperty("country")
    private String country;

    @JsonProperty("coord")
    private Coord coord;

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("population")
    private Integer population;

}