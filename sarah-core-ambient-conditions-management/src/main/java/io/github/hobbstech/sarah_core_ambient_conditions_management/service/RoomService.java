package io.github.hobbstech.sarah_core_ambient_conditions_management.service;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.Room;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.DetailedRoomDto;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.RoomDto;
import io.github.hobbstech.sarah_core_utils.service.AbstractCrudService;

public interface RoomService extends AbstractCrudService<RoomDto, Room, Long> {
    DetailedRoomDto findDetailedById(Long roomId);

    Room turnLights(Long roomId, boolean turnOn);

    void turnLights(boolean turnOn);
}
