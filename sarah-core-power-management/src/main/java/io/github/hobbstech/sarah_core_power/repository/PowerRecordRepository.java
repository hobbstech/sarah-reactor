package io.github.hobbstech.sarah_core_power.repository;

import io.github.hobbstech.sarah_core_power.model.PowerRecord;
import io.github.hobbstech.sarah_core_utils.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;

public interface PowerRecordRepository extends BaseRepository<PowerRecord> {

    Collection<PowerRecord> findAllByDateCreatedAfter(Date startDate);

    @Query(value = "select pr from PowerRecord  pr where pr.room.id =:roomId order by pr.dateCreated desc ")
    Page<PowerRecord> findAllByRoomId(Pageable pageable, @Param("roomId") Long roomId);
}
