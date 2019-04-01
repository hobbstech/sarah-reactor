package io.github.hobbstech.weather_management.domain;

import io.github.hobbstech.sarah_core_utils.domain.BaseEntity;
import io.github.hobbstech.weather_management.integration.open_weather_map.current.dto.Coordinates;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@EqualsAndHashCode(callSuper = true)
public class ResidentialLocation extends BaseEntity {

    @Embedded
    private Coordinates coordinates;

    @Column
    private String cityName;

}
