package io.github.hobbstech.sarah_core_ambient_conditions_management.repository;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.Room;
import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.TemperatureSetpoint;
import io.github.hobbstech.sarah_core_utils.repository.BaseRepository;

public interface TemperatureSetpointRepository extends BaseRepository<TemperatureSetpoint> {

    TemperatureSetpoint findTop1ByRoomOrderByDateCreatedDesc(Room room);

}
