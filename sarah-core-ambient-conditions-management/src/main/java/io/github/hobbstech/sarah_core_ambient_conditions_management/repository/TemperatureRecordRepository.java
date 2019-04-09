package io.github.hobbstech.sarah_core_ambient_conditions_management.repository;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.Room;
import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.TemperatureRecord;
import io.github.hobbstech.sarah_core_utils.repository.BaseRepository;

import java.time.LocalDate;
import java.util.Collection;

public interface TemperatureRecordRepository extends BaseRepository<TemperatureRecord> {

    Collection<TemperatureRecord> findAllByDate(LocalDate date);

    Collection<TemperatureRecord> findAllByDateAndRoom(LocalDate date, Room room);

}
