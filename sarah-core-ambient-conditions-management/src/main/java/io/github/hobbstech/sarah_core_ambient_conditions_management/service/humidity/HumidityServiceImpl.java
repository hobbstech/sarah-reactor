package io.github.hobbstech.sarah_core_ambient_conditions_management.service.humidity;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.HumidityRecord;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.HumidityDto;
import io.github.hobbstech.sarah_core_ambient_conditions_management.repository.HumidityRecordRepository;
import io.github.hobbstech.sarah_core_ambient_conditions_management.service.RoomService;
import lombok.val;

import static java.util.Objects.requireNonNull;

public class HumidityServiceImpl implements HumidityService {

    private final HumidityRecordRepository humidityRecordRepository;

    private final RoomService roomService;

    public HumidityServiceImpl(HumidityRecordRepository humidityRecordRepository, RoomService roomService) {
        this.humidityRecordRepository = humidityRecordRepository;
        this.roomService = roomService;
    }

    @Override
    public HumidityRecord saveHumidity(HumidityDto humidityDto) {
        requireNonNull(humidityDto, "Humidity dto should not be null");
        val humidityRecord = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .convertValue(humidityDto, HumidityRecord.class);

        val room = roomService.findById(humidityDto.getRoomId());

        humidityRecord.setRoom(room);

        return humidityRecordRepository.save(humidityRecord);
    }
}
