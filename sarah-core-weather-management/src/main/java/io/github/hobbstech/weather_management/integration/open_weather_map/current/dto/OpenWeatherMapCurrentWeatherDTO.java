package io.github.hobbstech.weather_management.integration.open_weather_map.current.dto;

import io.github.hobbstech.weather_management.domain.WeatherForTheDay;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Data
public class OpenWeatherMapCurrentWeatherDTO {

    private Long dt;

    private Coord coord;

    private List<WeatherItem> weather;

    private String name;

    private Long cod;

    private Main main;

    private Clouds clouds;

    private Long id;

    private Sys sys;

    private String base;

    private Wind wind;

    public WeatherForTheDay convertToWeatherForTheDay() {
        return WeatherForTheDay.builder()
                .cloudCover((double) clouds.getAll())
                .description(weather.get(0).getDescription())
                .maxTemperature(main.getTempMax())
                .minTemperature(main.getTempMin())
                .pressure(main.getPressure())
                .requestedTime(convertFromEpochMillSeconds(dt))
                .sunrise(convertFromEpochMillSeconds(sys.getSunrise()))
                .sunset(convertFromEpochMillSeconds(sys.getSunset()))
                .temperature(main.getTemp())
                .windSpeed(wind.getSpeed())
                .build();

    }

    private LocalDateTime convertFromEpochMillSeconds(Long seconds) {
        Instant instant = Instant.ofEpochSecond(seconds);
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }


}