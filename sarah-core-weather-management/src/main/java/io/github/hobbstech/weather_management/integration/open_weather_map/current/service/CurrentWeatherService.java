package io.github.hobbstech.weather_management.integration.open_weather_map.current.service;

import io.github.hobbstech.weather_management.integration.open_weather_map.current.dto.OpenWeatherMapCurrentForecastWeatherDTO;
import io.github.hobbstech.weather_management.integration.open_weather_map.current.dto.OpenWeatherMapCurrentWeatherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(url = "${open-weather-map.base-url}", name = "open-weather-map-service")
public interface CurrentWeatherService {

    @GetMapping("/weather")
    OpenWeatherMapCurrentWeatherDTO findByCityName(@RequestParam("q") String cityName,
                                                   @RequestParam("APPID") String apiKey);

    @GetMapping("/weather")
    OpenWeatherMapCurrentWeatherDTO findByCoordinates(@RequestParam("lon") Double longitude,
                                                      @RequestParam("lat") Double latitude,
                                                      @RequestParam("APPID") String apiKey);

    @GetMapping("/forecast")
    OpenWeatherMapCurrentForecastWeatherDTO findForecastByCoordinates(@RequestParam("lat") Double latitude,
                                                                      @RequestParam("lon") Double longitude,
                                                                      @RequestParam("APPID") String apiKey);

    @GetMapping("/forecast")
    OpenWeatherMapCurrentForecastWeatherDTO findForecastByCityName(@RequestParam("q") String cityName,
                                                                   @RequestParam("APPID") String apiKey);
}
