package io.github.hobbstech.sarah_core_ambient_conditions_management.dto;

import io.github.hobbstech.sarah_core_ambient_conditions_management.service.gas.TypeOfGas;
import io.github.hobbstech.sarah_core_utils.domain.BaseEntity;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetailedRoomDto extends BaseEntity {

    private String name;

    private Double temperature;

    private Boolean waterSpillagePresent;

    private TypeOfGas typeOfGas;

    private Double humidity;

    private Boolean motionDetected;

    private Boolean lightsOn;

    private Boolean flameDetected;

}
