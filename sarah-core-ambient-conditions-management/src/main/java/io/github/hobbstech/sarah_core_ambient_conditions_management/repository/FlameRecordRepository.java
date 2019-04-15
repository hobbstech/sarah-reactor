package io.github.hobbstech.sarah_core_ambient_conditions_management.repository;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.FlameRecord;
import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.Room;
import io.github.hobbstech.sarah_core_utils.repository.BaseRepository;

import java.util.Optional;

public interface FlameRecordRepository extends BaseRepository<FlameRecord> {

    Optional<FlameRecord> findTop1ByRoomOrderByDateCreatedDesc(Room room);

}
