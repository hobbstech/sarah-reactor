package io.github.hobbstech.sarah_core_ambient_conditions_management.service.motion;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.MotionSensorRecord;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.MotionSensorDto;
import io.github.hobbstech.sarah_core_ambient_conditions_management.repository.MotionSensorRecordRepository;
import io.github.hobbstech.sarah_core_ambient_conditions_management.service.RoomService;
import lombok.val;

import static java.util.Objects.requireNonNull;

public class MotionSensorServiceImpl implements MotionSensorService {

    private final MotionSensorRecordRepository motionSensorRecordRepository;

    private final RoomService roomService;

    private final MotionSensorActuator motionSensorActuator;

    public MotionSensorServiceImpl(MotionSensorRecordRepository motionSensorRecordRepository, RoomService roomService, MotionSensorActuator motionSensorActuator) {
        this.motionSensorRecordRepository = motionSensorRecordRepository;
        this.roomService = roomService;
        this.motionSensorActuator = motionSensorActuator;
    }

    @Override
    public MotionSensorRecord saveMotionSensor(MotionSensorDto motionSensorDto) {
        requireNonNull(motionSensorDto, "MotionSensor dto should not be null");
        val motionSensorRecord = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .convertValue(motionSensorDto, MotionSensorRecord.class);

        val room = roomService.findById(motionSensorDto.getRoomId());

        motionSensorRecord.setRoom(room);

        val persistedMotionSensorRecord = motionSensorRecordRepository.save(motionSensorRecord);
        motionSensorActuator.actuateMotionSensor(persistedMotionSensorRecord);
        return persistedMotionSensorRecord;
    }
}
