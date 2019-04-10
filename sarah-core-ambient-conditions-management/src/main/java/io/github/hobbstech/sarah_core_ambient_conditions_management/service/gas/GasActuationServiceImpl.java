package io.github.hobbstech.sarah_core_ambient_conditions_management.service.gas;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.GasRecord;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GasActuationServiceImpl implements GasActuationService {
    @Override
    public void actuateGases(GasRecord gasRecord) {
        log.info("---> Actuating gasses");
    }
}
