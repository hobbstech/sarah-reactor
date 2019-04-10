package io.github.hobbstech.sarah_core_ambient_conditions_management.service.flame;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.FlameRecord;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FlameActuationServiceImpl implements FlameActuationService {
    @Override
    public void actuateFlame(FlameRecord flameRecord) {
        log.info("---> Actuating flame");
    }
}
