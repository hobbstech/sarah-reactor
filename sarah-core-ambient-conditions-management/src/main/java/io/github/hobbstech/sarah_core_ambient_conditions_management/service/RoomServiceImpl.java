package io.github.hobbstech.sarah_core_ambient_conditions_management.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.Room;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.DetailedRoomDto;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.RoomDto;
import io.github.hobbstech.sarah_core_ambient_conditions_management.repository.*;
import io.github.hobbstech.sarah_core_utils.exceptions.RecordNotFoundException;
import lombok.val;

import java.util.Collection;

public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    private final TemperatureRecordRepository temperatureRecordRepository;

    private final HumidityRecordRepository humidityRecordRepository;

    private final FlameRecordRepository flameRecordRepository;

    private final GasRecordRepository gasRecordRepository;

    private final MotionSensorRecordRepository motionSensorRecordRepository;

    private final MoistureRecordRepository moistureRecordRepository;

    public RoomServiceImpl(RoomRepository roomRepository, TemperatureRecordRepository temperatureRecordRepository,
                           HumidityRecordRepository humidityRecordRepository, FlameRecordRepository flameRecordRepository,
                           GasRecordRepository gasRecordRepository, MotionSensorRecordRepository motionSensorRecordRepository,
                           MoistureRecordRepository moistureRecordRepository) {
        this.roomRepository = roomRepository;
        this.temperatureRecordRepository = temperatureRecordRepository;
        this.humidityRecordRepository = humidityRecordRepository;
        this.flameRecordRepository = flameRecordRepository;
        this.gasRecordRepository = gasRecordRepository;
        this.motionSensorRecordRepository = motionSensorRecordRepository;
        this.moistureRecordRepository = moistureRecordRepository;
    }

    @Override
    public Room save(RoomDto roomDto) {
        val room = new ObjectMapper().convertValue(roomDto, Room.class);
        return roomRepository.save(room);
    }

    @Override
    public Room update(Long id, RoomDto roomDto) {
        val room = findById(id);
        room.setName(roomDto.getName());
        return roomRepository.save(room);
    }

    @Override
    public void delete(Long id) {
        val room = findById(id);
        roomRepository.delete(room);
    }

    @Override
    public Collection<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Room record was not found"));
    }

    @Override
    public DetailedRoomDto findDetailedById(Long roomId) {
        val room = findById(roomId);
        val detailedRoomDto =
                new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                        .convertValue(room, DetailedRoomDto.class);

        temperatureRecordRepository.findTop1ByRoomOrderByDateCreatedDesc(room)
                .ifPresent(temperatureRecord -> detailedRoomDto.setTemperature(temperatureRecord.getTemperature()));

        humidityRecordRepository.findTop1ByRoomOrderByDateCreatedDesc(room)
                .ifPresent(humidityRecord -> detailedRoomDto.setHumidity(humidityRecord.getHumidity()));

        flameRecordRepository.findTop1ByRoomOrderByDateCreatedDesc(room)
                .ifPresent(flameRecord -> detailedRoomDto.setFlameDetected(flameRecord.getFlameDetected()));

        gasRecordRepository.findTop1ByRoomOrderByDateCreatedDesc(room)
                .ifPresent(gasRecord -> detailedRoomDto.setTypeOfGas(gasRecord.getTypeOfGas()));

        motionSensorRecordRepository.findTop1ByRoomOrderByDateCreatedDesc(room)
                .ifPresent(motionSensorRecord -> detailedRoomDto.setMotionDetected(motionSensorRecord.getMotionDetected()));

        moistureRecordRepository.findTop1ByRoomOrderByDateCreatedDesc(room)
                .ifPresent(moistureRecord -> detailedRoomDto.setWaterSpillagePresent(moistureRecord.getWaterSpillagePresent()));


        return detailedRoomDto;
    }

    @Override
    public Room turnLights(Long roomId, boolean turnOn) {
        var room = roomRepository.findById(roomId).orElseThrow();
        room.setLightsOn(turnOn);
        return roomRepository.save(room);
    }

    @Override
    public void turnLights(boolean turnOn) {
        var rooms = roomRepository.findAll();
        rooms.forEach(room -> room.setLightsOn(turnOn));
        roomRepository.saveAll(rooms);
    }
}
