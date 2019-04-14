package io.github.hobbstech.sarah_core_ambient_conditions_management.service.motion;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.MotionSensorRecord;
import org.springframework.scheduling.annotation.Async;

public interface MotionSensorActuator {
    @Async
    void actuateMotionSensor(MotionSensorRecord motionSensorRecord);

}
