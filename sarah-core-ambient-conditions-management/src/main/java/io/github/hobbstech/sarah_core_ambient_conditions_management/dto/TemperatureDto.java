package io.github.hobbstech.sarah_core_ambient_conditions_management.dto;

import lombok.Data;

@Data
public class TemperatureDto {

    private Double temperature;

    private String roomName;

    private Long roomId;

}
