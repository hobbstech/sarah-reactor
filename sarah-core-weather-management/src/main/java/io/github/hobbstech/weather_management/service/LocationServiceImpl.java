package io.github.hobbstech.weather_management.service;

import io.github.hobbstech.weather_management.domain.ResidentialLocation;
import io.github.hobbstech.weather_management.integration.open_weather_map.current.dto.Coordinates;
import io.github.hobbstech.weather_management.repository.ResidentialLocationRepository;
import io.github.hobbstech.weather_management.service.location.LocationDto;
import lombok.val;

public class LocationServiceImpl implements LocationService {

    private final ResidentialLocationRepository residentialLocationRepository;

    public LocationServiceImpl(ResidentialLocationRepository residentialLocationRepository) {
        this.residentialLocationRepository = residentialLocationRepository;
    }

    @Override
    public ResidentialLocation saveLocation(LocationDto locationDto) {

        val coordinates = new Coordinates();
        coordinates.setLatitude(Double.valueOf(locationDto.getLatitude()));
        coordinates.setLongitude(Double.valueOf(locationDto.getLongitude()));

        val residentialLocation = new ResidentialLocation(coordinates, locationDto.getCityName());

        residentialLocationRepository.deleteAll();

        return residentialLocationRepository.save(residentialLocation);

    }
}
