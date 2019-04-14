package io.github.hobbstech.sarah_core_ambient_conditions_management.service.moistuire;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.MoistureRecord;
import org.springframework.scheduling.annotation.Async;

public interface MoistureActuator {
    @Async
    void actuateMoisture(MoistureRecord moistureRecord);

}
