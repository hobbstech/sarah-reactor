package io.github.hobbstech.sara_api.ambient_conditions;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.TemperatureSetpoint;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.TemperatureSetPointDto;
import io.github.hobbstech.sarah_core_ambient_conditions_management.service.TemperatureRegulationService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class TemperatureRegulationRestController {

    private final TemperatureRegulationService temperatureRegulationService;

    public TemperatureRegulationRestController(TemperatureRegulationService temperatureRegulationService) {
        this.temperatureRegulationService = temperatureRegulationService;
    }

    @PostMapping("/v1/room-temp/{roomId}/set-point")
    public TemperatureSetpoint setRoomTempSetPoint(@RequestBody TemperatureSetPointDto temperatureSetPointDto,
                                                   @PathVariable("roomId") Long roomId) {
        return temperatureRegulationService.setTempSetpoint(roomId, temperatureSetPointDto);
    }

    @PostMapping("/v1/house-temp/set-point")
    public Collection<TemperatureSetpoint> setHouseTempSetPoint(@RequestBody TemperatureSetPointDto temperatureSetPointDto) {
        return temperatureRegulationService.setHouseTemperature(temperatureSetPointDto);
    }

    @GetMapping("/v1/room-temp/{roomId}/set-point")
    public TemperatureSetpoint getRoomTempSetPoint(@PathVariable("roomId") Long roomId) {
        return temperatureRegulationService.getRoomTemperatureSetPoint(roomId);
    }

}
