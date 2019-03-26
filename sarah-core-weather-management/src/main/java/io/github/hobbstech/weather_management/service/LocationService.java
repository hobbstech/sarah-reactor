package io.github.hobbstech.weather_management.service;

import io.github.hobbstech.weather_management.domain.ResidentialLocation;
import io.github.hobbstech.weather_management.service.location.LocationDto;

public interface LocationService {

    ResidentialLocation saveLocation(LocationDto locationDto);

}
