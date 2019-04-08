package io.github.hobbstech.sara_api.weather;

import io.github.hobbstech.weather_management.domain.ResidentialLocation;
import io.github.hobbstech.weather_management.service.LocationService;
import io.github.hobbstech.weather_management.service.location.LocationDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationRestController {

    private final LocationService locationService;

    public LocationRestController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/v1/location")
    public ResidentialLocation saveLocation(@RequestBody LocationDto locationDto) {
        return locationService.saveLocation(locationDto);
    }

}
