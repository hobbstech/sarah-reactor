package io.github.hobbstech.weather_management.service;

import io.github.hobbstech.weather_management.domain.WeatherForTheDay;
import io.github.hobbstech.weather_management.integration.open_weather_map.current.dto.Coordinates;

import java.util.Collection;

public interface WeatherForTheDayProvider {

    WeatherForTheDay getWeatherForToday(String cityName);

    WeatherForTheDay getWeatherForToday(Coordinates coordinates);

    WeatherForTheDay getWeatherForTodayForMyLocation();

    Collection<WeatherForTheDay> getWeatherForecastForMyLocation();

    Collection<WeatherForTheDay> getWeatherForecast(String cityName);

    Collection<WeatherForTheDay> getWeatherForecast(Coordinates coordinates);

}
