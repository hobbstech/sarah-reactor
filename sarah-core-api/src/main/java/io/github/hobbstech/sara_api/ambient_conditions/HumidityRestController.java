package io.github.hobbstech.sara_api.ambient_conditions;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.HumidityRecord;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.HumidityDto;
import io.github.hobbstech.sarah_core_ambient_conditions_management.service.humidity.HumidityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HumidityRestController {

    private final HumidityService humidityService;

    public HumidityRestController(HumidityService humidityService) {
        this.humidityService = humidityService;
    }

    @PostMapping("/v1/humidity")
    public HumidityRecord saveRecord(@RequestBody HumidityDto humidityDto) {
        return humidityService.saveHumidity(humidityDto);
    }

}
