package io.github.hobbstech.sara_api.ambient_conditions;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.MotionSensorRecord;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.MotionSensorDto;
import io.github.hobbstech.sarah_core_ambient_conditions_management.service.motion.MotionSensorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MotionSensorRestController {

    private final MotionSensorService motionSensorService;

    public MotionSensorRestController(MotionSensorService motionSensorService) {
        this.motionSensorService = motionSensorService;
    }

    @PostMapping("/v1/room-motion-sensor")
    public MotionSensorRecord saveRecord(@RequestBody MotionSensorDto motionSensorDto) {
        return motionSensorService.saveMotionSensor(motionSensorDto);
    }

}
