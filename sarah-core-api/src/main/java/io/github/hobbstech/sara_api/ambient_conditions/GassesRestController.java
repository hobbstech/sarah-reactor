package io.github.hobbstech.sara_api.ambient_conditions;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.GasRecord;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.GassesDto;
import io.github.hobbstech.sarah_core_ambient_conditions_management.service.gas.GassesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GassesRestController {

    private final GassesService gassesService;

    public GassesRestController(GassesService gassesService) {
        this.gassesService = gassesService;
    }

    @PostMapping("/v1/gasses")
    public GasRecord saveGasRecord(@RequestBody GassesDto gassesDto) {
        return gassesService.saveGasRecord(gassesDto);
    }

}
