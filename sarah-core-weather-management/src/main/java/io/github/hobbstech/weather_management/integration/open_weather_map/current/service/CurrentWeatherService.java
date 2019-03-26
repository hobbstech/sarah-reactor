package io.github.hobbstech.weather_management.integration.open_weather_map.current.service;

import io.github.hobbstech.weather_management.integration.open_weather_map.current.dto.OpenWeatherMapCurrentWeatherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;


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
    Collection<OpenWeatherMapCurrentWeatherDTO> findForecastByCoordinates(@RequestParam("lat") Double latitude,
                                                                          @RequestParam("lon") Double longitude,
                                                                          @RequestParam("APPID") String apiKey);

    @GetMapping("/forecast")
    Collection<OpenWeatherMapCurrentWeatherDTO> findForecastByCityName(@RequestParam("q") String cityName,
                                                                       @RequestParam("APPID") String apiKey);
}
