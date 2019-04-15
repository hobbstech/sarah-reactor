package io.github.hobbstech.sarah_core_ambient_conditions_management.repository;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.MoistureRecord;
import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.Room;
import io.github.hobbstech.sarah_core_utils.repository.BaseRepository;

import java.util.Optional;

public interface MoistureRecordRepository extends BaseRepository<MoistureRecord> {

    Optional<MoistureRecord> findTop1ByRoomOrderByDateCreatedDesc(Room room);


}
