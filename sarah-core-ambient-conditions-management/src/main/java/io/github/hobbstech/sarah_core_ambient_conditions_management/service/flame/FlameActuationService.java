package io.github.hobbstech.sarah_core_ambient_conditions_management.service.flame;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.FlameRecord;
import org.springframework.scheduling.annotation.Async;

public interface FlameActuationService {

    @Async
    void actuateFlame(FlameRecord flameRecord);

}
