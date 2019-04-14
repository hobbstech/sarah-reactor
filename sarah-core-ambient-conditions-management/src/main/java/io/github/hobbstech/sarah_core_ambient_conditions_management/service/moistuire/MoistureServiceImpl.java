package io.github.hobbstech.sarah_core_ambient_conditions_management.service.moistuire;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.MoistureRecord;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.MoistureDto;
import io.github.hobbstech.sarah_core_ambient_conditions_management.repository.MoistureRecordRepository;
import io.github.hobbstech.sarah_core_ambient_conditions_management.service.RoomService;
import lombok.val;

import static java.util.Objects.requireNonNull;

public class MoistureServiceImpl implements MoistureService {

    private final MoistureRecordRepository moistureRecordRepository;

    private final RoomService roomService;

    private final MoistureActuator moistureActuator;

    public MoistureServiceImpl(MoistureRecordRepository moistureRecordRepository, RoomService roomService, MoistureActuator moistureActuator) {
        this.moistureRecordRepository = moistureRecordRepository;
        this.roomService = roomService;
        this.moistureActuator = moistureActuator;
    }

    @Override
    public MoistureRecord saveMoisture(MoistureDto moistureDto) {
        requireNonNull(moistureDto, "Moisture dto should not be null");
        val moistureRecord = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .convertValue(moistureDto, MoistureRecord.class);

        val room = roomService.findById(moistureDto.getRoomId());

        moistureRecord.setRoom(room);

        val persistedMoistureRecord = moistureRecordRepository.save(moistureRecord);
        moistureActuator.actuateMoisture(persistedMoistureRecord);
        return persistedMoistureRecord;
    }
}
