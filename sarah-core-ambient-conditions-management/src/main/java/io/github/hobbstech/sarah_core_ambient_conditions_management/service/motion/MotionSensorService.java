package io.github.hobbstech.sarah_core_ambient_conditions_management.service.motion;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.MotionSensorRecord;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.MotionSensorDto;

public interface MotionSensorService {

    MotionSensorRecord saveMotionSensor(MotionSensorDto MotionSensorDto);

}
