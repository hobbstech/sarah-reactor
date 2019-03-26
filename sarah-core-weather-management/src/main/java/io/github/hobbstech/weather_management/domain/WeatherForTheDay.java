package io.github.hobbstech.weather_management.domain;

import lombok.*;
import tech.hobbs.sarah_core_utils.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
public class WeatherForTheDay extends BaseEntity {

    @Column
    private Double temperature;

    @Column
    private Double pressure;

    @Column
    private Double maxTemperature;

    @Column
    private Double minTemperature;

    @Column
    private Double windSpeed;

    @Column
    private LocalDateTime sunrise;

    @Column
    private LocalDateTime sunset;

    @Column
    private Double cloudCover;

    @Column
    private String description;

    @Column
    private LocalDateTime requestedTime;

}
