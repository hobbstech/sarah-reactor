package io.github.hobbstech.sarah_core_ambient_conditions_management.service;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.TemperatureSetpoint;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.TemperatureSetPointDto;

import java.util.Collection;

public interface TemperatureRegulationService {

    TemperatureSetpoint setTempSetpoint(Long roomId, TemperatureSetPointDto temperatureSetPointDto);

    Collection<TemperatureSetpoint> setHouseTemperature(TemperatureSetPointDto temperatureSetpoint);

    TemperatureSetpoint getRoomTemperatureSetPoint(Long roomId);


}
