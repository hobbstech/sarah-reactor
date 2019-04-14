package io.github.hobbstech.sara_api.ambient_conditions;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.MoistureRecord;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.MoistureDto;
import io.github.hobbstech.sarah_core_ambient_conditions_management.service.moistuire.MoistureService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoistureRestController {

    private final MoistureService moistureService;

    public MoistureRestController(MoistureService moistureService) {
        this.moistureService = moistureService;
    }

    @PostMapping("/v1/moisture")
    public MoistureRecord saveRecord(@RequestBody MoistureDto moistureDto) {
        return moistureService.saveMoisture(moistureDto);
    }

}
