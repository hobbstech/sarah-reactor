package io.github.hobbstech.sara_api.ambient_conditions;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.Room;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.DetailedRoomDto;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.RoomDto;
import io.github.hobbstech.sarah_core_ambient_conditions_management.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class RoomsRestController {

    private final RoomService roomService;

    public RoomsRestController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/v1/rooms")
    public Room create(@RequestBody RoomDto roomDto) {
        return roomService.save(roomDto);
    }

    @PutMapping("/v1/rooms/{roomId}")
    public Room update(@RequestBody RoomDto roomDto, @PathVariable("roomId") Long roomId) {
        return roomService.update(roomId, roomDto);
    }

    @DeleteMapping("/v1/rooms/{roomId}")
    public void delete(@PathVariable("roomId") Long roomId) {
        roomService.delete(roomId);
    }

    @GetMapping("/v1/rooms")
    public Collection<Room> getAll() {
        return roomService.findAll();
    }

    @GetMapping("/v1/rooms/{roomId}")
    public DetailedRoomDto findById(@PathVariable("roomId") Long roomId) {
        return roomService.findDetailedById(roomId);
    }

}
