package io.github.hobbstech.weather_management.integration.open_weather_map.current.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Sys {

	@JsonProperty("pod")
	private String pod;

	@JsonProperty("country")
	private String country;

	@JsonProperty("sunrise")
	private Long sunrise;

	@JsonProperty("sunset")
	private Long sunset;

	@JsonProperty("message")
	private Double message;

}