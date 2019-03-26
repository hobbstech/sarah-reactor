package io.github.hobbstech.weather_management.service.config;

import io.github.hobbstech.weather_management.integration.open_weather_map.current.service.CurrentWeatherService;
import io.github.hobbstech.weather_management.repository.ResidentialLocationRepository;
import io.github.hobbstech.weather_management.repository.WeatherForTheDayRepository;
import io.github.hobbstech.weather_management.service.LocationService;
import io.github.hobbstech.weather_management.service.LocationServiceImpl;
import io.github.hobbstech.weather_management.service.WeatherForTheDayProvider;
import io.github.hobbstech.weather_management.service.WeatherForTheDayProviderImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeatherConfig {


    @Value("${open-weather-map.api-key}")
    private String apiKey;

    @Bean
    public WeatherForTheDayProvider weatherForTheDayProvider(CurrentWeatherService currentWeatherService,
                                                             ResidentialLocationRepository residentialLocationRepository,
                                                             WeatherForTheDayRepository weatherForTheDayRepository) {
        return new WeatherForTheDayProviderImpl(currentWeatherService, apiKey, residentialLocationRepository, weatherForTheDayRepository);
    }

    @Bean
    public LocationService locationService(ResidentialLocationRepository residentialLocationRepository) {
        return new LocationServiceImpl(residentialLocationRepository);
    }

}
