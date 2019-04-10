package io.github.hobbstech.sara_api.ambient_conditions;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.FlameRecord;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.FlameDto;
import io.github.hobbstech.sarah_core_ambient_conditions_management.service.flame.FlameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlameRestController {

    private final FlameService flameService;

    public FlameRestController(FlameService flameService) {
        this.flameService = flameService;
    }

    @PostMapping("/v1/flames")
    public FlameRecord saveFlameRecord(@RequestBody FlameDto flameDto) {
        return flameService.saveFlameRecord(flameDto);
    }

}
