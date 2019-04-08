package io.github.hobbstech.weather_management.integration.open_weather_map.current.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherItem {

	@JsonProperty("icon")
	private String icon;

	@JsonProperty("description")
	private String description;

	@JsonProperty("main")
	private String main;

	@JsonProperty("id")
	private Integer id;

}