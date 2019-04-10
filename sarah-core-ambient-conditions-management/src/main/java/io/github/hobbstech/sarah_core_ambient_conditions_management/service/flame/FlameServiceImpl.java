package io.github.hobbstech.sarah_core_ambient_conditions_management.service.flame;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.FlameRecord;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.FlameDto;
import io.github.hobbstech.sarah_core_ambient_conditions_management.repository.FlameRecordRepository;
import io.github.hobbstech.sarah_core_ambient_conditions_management.service.RoomService;
import lombok.val;

public class FlameServiceImpl implements FlameService {

    private final RoomService roomService;

    private final FlameRecordRepository flameRecordRepository;

    private final FlameActuationService flameActuationService;

    public FlameServiceImpl(RoomService roomService, FlameRecordRepository flameRecordRepository,
                            FlameActuationService flameActuationService) {
        this.roomService = roomService;
        this.flameRecordRepository = flameRecordRepository;
        this.flameActuationService = flameActuationService;
    }

    @Override
    public FlameRecord saveFlameRecord(FlameDto flameDto) {

        val flameRecord = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .convertValue(flameDto, FlameRecord.class);

        val room = roomService.findById(flameDto.getRoomId());

        flameRecord.setRoom(room);

        val persistedFlameRecord = flameRecordRepository.save(flameRecord);

        flameActuationService.actuateFlame(persistedFlameRecord);


        return persistedFlameRecord;
    }
}
