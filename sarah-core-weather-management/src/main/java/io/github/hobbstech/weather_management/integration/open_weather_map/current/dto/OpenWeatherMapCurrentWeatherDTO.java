package io.github.hobbstech.weather_management.integration.open_weather_map.current.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.hobbstech.weather_management.domain.WeatherForTheDay;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    private Rain rain;

    @JsonProperty("dt_txt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date date;



    public WeatherForTheDay convertToWeatherForTheDay() {
        return WeatherForTheDay.builder()
                .cloudCover(clouds.getAll())
                .description(weather.get(0).getDescription())
                .maxTemperature(convertTempDegrees(main.getTempMax()))
                .minTemperature(convertTempDegrees(main.getTempMin()))
                .pressure(main.getPressure())
                .requestedTime(convertFromEpochMillSeconds(dt))
                .sunrise(convertFromEpochMillSeconds(sys.getSunrise()))
                .sunset(convertFromEpochMillSeconds(sys.getSunset()))
                .temperature(convertTempDegrees(main.getTemp()))
                .windSpeed(wind.getSpeed())
                .date(date)
                .build();

    }

    private LocalDateTime convertFromEpochMillSeconds(Long seconds) {
        if (Objects.isNull(seconds))
            return null;
        Instant instant = Instant.ofEpochSecond(seconds);
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    private Double convertTempDegrees(Double kevinTemperature) {
        return kevinTemperature - 273;
    }


}