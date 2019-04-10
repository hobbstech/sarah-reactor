package io.github.hobbstech.sarah_core_ambient_conditions_management.service.flame;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.FlameRecord;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.FlameDto;

public interface FlameService {

    FlameRecord saveFlameRecord(FlameDto flameDto);

}
