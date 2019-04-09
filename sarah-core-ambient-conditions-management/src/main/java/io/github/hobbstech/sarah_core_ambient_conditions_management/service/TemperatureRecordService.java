package io.github.hobbstech.sarah_core_ambient_conditions_management.service;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.TemperatureRecord;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.TemperatureDto;

import java.util.Collection;
import java.util.Date;

public interface TemperatureRecordService {

    TemperatureRecord saveTemperature(TemperatureDto temperatureDto);

    Collection<TemperatureRecord> getTemperatureRecordsPerDay(Date date);

    Collection<TemperatureRecord> getTemperatureRecordsPerDayForRoom(Date date, Long roomId);
}
