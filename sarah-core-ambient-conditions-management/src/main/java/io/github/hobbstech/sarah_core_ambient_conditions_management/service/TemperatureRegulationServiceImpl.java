package io.github.hobbstech.sarah_core_ambient_conditions_management.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.TemperatureSetpoint;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.TemperatureSetPointDto;
import io.github.hobbstech.sarah_core_ambient_conditions_management.repository.TemperatureSetpointRepository;
import lombok.val;

import java.util.Collection;
import java.util.Objects;

public class TemperatureRegulationServiceImpl implements TemperatureRegulationService {

    private final TemperatureSetpointRepository temperatureSetpointRepository;

    private final RoomService roomService;

    public TemperatureRegulationServiceImpl(TemperatureSetpointRepository temperatureSetpointRepository,
                                            RoomService roomService) {
        this.temperatureSetpointRepository = temperatureSetpointRepository;
        this.roomService = roomService;
    }

    @Override
    public TemperatureSetpoint setTempSetpoint(Long roomId, TemperatureSetPointDto temperatureSetPointDto) {
        val room = roomService.findById(roomId);

        TemperatureSetpoint temperatureSetPoint = temperatureSetpointRepository.findTop1ByRoomOrderByDateCreatedDesc(room);

        if (Objects.isNull(temperatureSetPoint))
            temperatureSetPoint = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .convertValue(temperatureSetPointDto, TemperatureSetpoint.class);
        else {
            temperatureSetPoint.setTemperatureValue(temperatureSetPointDto.getTemperatureValue());
        }

        temperatureSetPoint.setRoom(room);

        return temperatureSetpointRepository.save(temperatureSetPoint);
    }

    @Override
    public Collection<TemperatureSetpoint> setHouseTemperature(final TemperatureSetPointDto temperatureSetPointDto) {

        val roomsTempSetPoints = temperatureSetpointRepository.findAll();
        roomsTempSetPoints.forEach(temperatureSetpoint -> temperatureSetpoint.setTemperatureValue(temperatureSetPointDto.getTemperatureValue()));
        return temperatureSetpointRepository.saveAll(roomsTempSetPoints);
    }

    @Override
    public TemperatureSetpoint getRoomTemperatureSetPoint(Long roomId) {
        val room = roomService.findById(roomId);
        return temperatureSetpointRepository.findTop1ByRoomOrderByDateCreatedDesc(room);
    }
}
