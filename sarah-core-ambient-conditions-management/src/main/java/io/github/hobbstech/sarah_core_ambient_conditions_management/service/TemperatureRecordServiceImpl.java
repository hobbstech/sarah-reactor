package io.github.hobbstech.sarah_core_ambient_conditions_management.service;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.TemperatureRecord;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.TemperatureDto;
import io.github.hobbstech.sarah_core_ambient_conditions_management.repository.TemperatureRecordRepository;

import java.util.Collection;
import java.util.Date;

public class TemperatureRecordServiceImpl implements TemperatureRecordService {

    private final TemperatureRecordRepository temperatureRecordRepository;

    public TemperatureRecordServiceImpl(TemperatureRecordRepository temperatureRecordRepository) {
        this.temperatureRecordRepository = temperatureRecordRepository;
    }

    @Override
    public TemperatureRecord saveTemperature(TemperatureDto temperatureDto) {
        return null;
    }

    @Override
    public Collection<TemperatureRecord> getTemperatureRecords(Date date) {
        return null;
    }
}
