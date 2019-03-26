package io.github.hobbstech.weather_management.integration.open_weather_map.current.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Coordinates {

    @Column
    private Double longitude;

    @Column
    private Double latitude;

}
