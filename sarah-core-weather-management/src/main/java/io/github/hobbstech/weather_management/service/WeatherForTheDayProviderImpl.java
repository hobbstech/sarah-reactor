package io.github.hobbstech.weather_management.service;

import io.github.hobbstech.sarah_core_utils.exceptions.RecordNotFoundException;
import io.github.hobbstech.weather_management.domain.ResidentialLocation;
import io.github.hobbstech.weather_management.domain.WeatherForTheDay;
import io.github.hobbstech.weather_management.integration.open_weather_map.current.dto.Coordinates;
import io.github.hobbstech.weather_management.integration.open_weather_map.current.dto.OpenWeatherMapCurrentWeatherDTO;
import io.github.hobbstech.weather_management.integration.open_weather_map.current.service.CurrentWeatherService;
import io.github.hobbstech.weather_management.repository.ResidentialLocationRepository;
import io.github.hobbstech.weather_management.repository.WeatherForTheDayRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Comparator.comparing;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toSet;

@Slf4j
public class WeatherForTheDayProviderImpl implements WeatherForTheDayProvider {

    private final CurrentWeatherService currentWeatherService;

    private final String apiKey;

    private final ResidentialLocationRepository residentialLocationRepository;

    private final WeatherForTheDayRepository weatherForTheDayRepository;

    public WeatherForTheDayProviderImpl(CurrentWeatherService currentWeatherService, String apiKey, ResidentialLocationRepository residentialLocationRepository, WeatherForTheDayRepository weatherForTheDayRepository) {
        this.currentWeatherService = currentWeatherService;
        this.apiKey = apiKey;
        this.residentialLocationRepository = residentialLocationRepository;
        this.weatherForTheDayRepository = weatherForTheDayRepository;
    }

    @Override
    public WeatherForTheDay getWeatherForToday(String cityName) {

        val openWeatherMapCurrentWeatherDTO = currentWeatherService.findByCityName(cityName, apiKey);
        log.info("------> response : {}", openWeatherMapCurrentWeatherDTO);

        return openWeatherMapCurrentWeatherDTO.convertToWeatherForTheDay();

    }

    @Override
    public WeatherForTheDay getWeatherForToday(Coordinates coordinates) {
        val openWeatherMapCurrentWeatherDTO = currentWeatherService
                .findByCoordinates(coordinates.getLongitude(), coordinates.getLatitude(), apiKey);

        log.info("------> response : {}", openWeatherMapCurrentWeatherDTO);

        return openWeatherMapCurrentWeatherDTO.convertToWeatherForTheDay();
    }

    @Override
    public WeatherForTheDay getWeatherForTodayForMyLocation() {

        log.info("---> Updating weather records");

        val residentialLocations = new ArrayList<ResidentialLocation>(residentialLocationRepository.findAll());

        if (residentialLocations.isEmpty())
            throw new RecordNotFoundException("Location record was not found");

        val residentialLocation = residentialLocations.stream()
                .max(comparing(ResidentialLocation::getDateCreated))
                .get();

        val coordinates = residentialLocation.getCoordinates();

        WeatherForTheDay weatherForTheDay;

        if (nonNull(coordinates) && nonNull(coordinates.getLatitude()) && nonNull(coordinates.getLongitude())) {
            weatherForTheDay = getWeatherForToday(coordinates);
        } else {
            weatherForTheDay = getWeatherForToday(residentialLocation.getCityName());
        }

        return weatherForTheDayRepository.save(weatherForTheDay);
    }

    @Override
    public Collection<WeatherForTheDay> getWeatherForecastForMyLocation() {

        val residentialLocations = new ArrayList<ResidentialLocation>(residentialLocationRepository.findAll());

        if (residentialLocations.isEmpty())
            throw new RecordNotFoundException("Location record was not found");

        val residentialLocation = residentialLocations.stream()
                .max(comparing(ResidentialLocation::getDateCreated))
                .get();

        val cityName = residentialLocation.getCityName();
        val coordinates = residentialLocation.getCoordinates();

        if (nonNull(coordinates) && nonNull(coordinates.getLongitude()) && nonNull(coordinates.getLatitude())) {
            return currentWeatherService.findForecastByCoordinates(coordinates.getLatitude(),
                    coordinates.getLongitude(), apiKey).stream()
                    .map(OpenWeatherMapCurrentWeatherDTO::convertToWeatherForTheDay)
                    .collect(toSet());
        } else {
            return currentWeatherService.findForecastByCityName(cityName, apiKey).stream()
                    .map(OpenWeatherMapCurrentWeatherDTO::convertToWeatherForTheDay)
                    .collect(toSet());
        }

    }

    @Override
    public Collection<WeatherForTheDay> getWeatherForecast(String cityName) {

        return currentWeatherService.findForecastByCityName(cityName, apiKey)
                .stream()
                .map(OpenWeatherMapCurrentWeatherDTO::convertToWeatherForTheDay)
                .collect(toSet());

    }

    @Override
    public Collection<WeatherForTheDay> getWeatherForecast(Coordinates coordinates) {
        return currentWeatherService
                .findForecastByCoordinates(coordinates.getLongitude(), coordinates.getLatitude(), apiKey)
                .stream()
                .map(OpenWeatherMapCurrentWeatherDTO::convertToWeatherForTheDay)
                .collect(toSet());
    }
}
