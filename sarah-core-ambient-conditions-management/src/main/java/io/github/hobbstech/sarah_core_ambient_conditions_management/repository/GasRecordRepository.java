package io.github.hobbstech.sarah_core_ambient_conditions_management.repository;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.GasRecord;
import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.Room;
import io.github.hobbstech.sarah_core_utils.repository.BaseRepository;

import java.util.Optional;

public interface GasRecordRepository extends BaseRepository<GasRecord> {

    Optional<GasRecord> findTop1ByRoomOrderByDateCreatedDesc(Room room);

}
