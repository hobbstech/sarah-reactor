package io.github.hobbstech.sarah_core_ambient_conditions_management.service.gas;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.GasRecord;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.GassesDto;

public interface GassesService {

    GasRecord saveGasRecord(GassesDto gassesDto);

}
