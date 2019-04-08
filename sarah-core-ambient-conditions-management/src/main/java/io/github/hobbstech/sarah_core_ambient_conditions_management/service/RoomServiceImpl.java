package io.github.hobbstech.sarah_core_ambient_conditions_management.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.Room;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.RoomDto;
import io.github.hobbstech.sarah_core_ambient_conditions_management.repository.RoomRepository;
import io.github.hobbstech.sarah_core_utils.exceptions.RecordNotFoundException;
import lombok.val;

public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room save(RoomDto roomDto) {
        val room = new ObjectMapper().convertValue(roomDto, Room.class);
        return roomRepository.save(room);
    }

    @Override
    public Room update(Long id, RoomDto roomDto) {
        val room = roomRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Room record was not found"));
        room.setName(roomDto.getName());
        room.setNumber(roomDto.getNumber());
        return roomRepository.save(room);
    }
}
