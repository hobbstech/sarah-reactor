package io.github.hobbstech.sara_api.ambient_conditions;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.TemperatureRecord;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.TemperatureDto;
import io.github.hobbstech.sarah_core_ambient_conditions_management.service.temprature.TemperatureRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;

@RestController
public class TemperatureRecordsRestController {

    private final TemperatureRecordService temperatureRecordService;

    public TemperatureRecordsRestController(TemperatureRecordService temperatureRecordService) {
        this.temperatureRecordService = temperatureRecordService;
    }

    @PostMapping("/v1/room-temperature")
    public TemperatureRecord saveRoomTemperature(@RequestBody TemperatureDto temperatureDto) {
        return temperatureRecordService.saveTemperature(temperatureDto);
    }

    @GetMapping("/v1/house-temperature")
    public Collection<TemperatureRecord> getTemperatureRecordsForDay(
            @RequestParam(name = "date") Date date) {
        return temperatureRecordService.getTemperatureRecordsPerDay(date);
    }

    @GetMapping("/v1/room-temperature/{roomId}")
    public Collection<TemperatureRecord> getTemperatureRecordsForDayForRoom(
            @RequestParam(name = "date") Date date, @PathVariable("roomId") Long roomId) {
        return temperatureRecordService.getTemperatureRecordsPerDayForRoom(date, roomId);
    }

    @GetMapping("/v1/house-temperature/today")
    public Collection<TemperatureRecord> getHouseTemperatureForToday() {
        return temperatureRecordService.getTemperatureRecordsPerDay(new Date());
    }

    @GetMapping("/v1/room-temperature/{roomId}/today")
    public Collection<TemperatureRecord> getRoomTemperatureForToday(@PathVariable("roomId") Long roomId) {
        return temperatureRecordService.getTemperatureRecordsPerDayForRoom(new Date(), roomId);
    }

}
