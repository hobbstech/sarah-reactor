package io.github.hobbstech.sarah_core_ambient_conditions_management.service.gas;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.GasRecord;
import org.springframework.scheduling.annotation.Async;

public interface GasActuationService {

    @Async
    void actuateGases(GasRecord gasRecord);

}
