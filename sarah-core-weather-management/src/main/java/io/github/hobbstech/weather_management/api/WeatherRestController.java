package io.github.hobbstech.weather_management.api;

import io.github.hobbstech.weather_management.domain.WeatherForTheDay;
import io.github.hobbstech.weather_management.integration.open_weather_map.current.dto.Coordinates;
import io.github.hobbstech.weather_management.service.WeatherForTheDayProvider;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class WeatherRestController {

    private final WeatherForTheDayProvider weatherForTheDayProvider;

    public WeatherRestController(WeatherForTheDayProvider weatherForTheDayProvider) {
        this.weatherForTheDayProvider = weatherForTheDayProvider;
    }

    @GetMapping("/v1/weather/today/my-location")
    public WeatherForTheDay weatherForTheDay() {
        return weatherForTheDayProvider.getWeatherForTodayForMyLocation();
    }

    @GetMapping(value = "/v1/weather/today", params = {"longitude", "latitude"})
    public WeatherForTheDay weatherForTheDay(@RequestParam Double longitude, @RequestParam Double latitude) {
        val coordinates = new Coordinates();
        coordinates.setLongitude(longitude);
        coordinates.setLatitude(latitude);
        return weatherForTheDayProvider.getWeatherForToday(coordinates);
    }

    @GetMapping(value = "/v1/weather/today/city", params = {"cityName"})
    public WeatherForTheDay weatherForTheDay(@RequestParam String cityName) {
        return weatherForTheDayProvider.getWeatherForToday(cityName);
    }

    @GetMapping("/v1/weather/forecast/my-location")
    public Collection<WeatherForTheDay> getWeatherForecast() {
        return weatherForTheDayProvider.getWeatherForecastForMyLocation();
    }

    @GetMapping(value = "/v1/forecast", params = {"longitude", "latitude"})
    public Collection<WeatherForTheDay> getWeatherForecast(@RequestParam Double longitude, @RequestParam Double latitude) {
        val coordinates = new Coordinates();
        coordinates.setLongitude(longitude);
        coordinates.setLatitude(latitude);
        return weatherForTheDayProvider.getWeatherForecast(coordinates);
    }

    @GetMapping(value = "/v1/forecast/today/city", params = {"cityName"})
    public Collection<WeatherForTheDay> getWeatherForecast(@RequestParam String cityName) {
        return weatherForTheDayProvider.getWeatherForecast(cityName);
    }

}
