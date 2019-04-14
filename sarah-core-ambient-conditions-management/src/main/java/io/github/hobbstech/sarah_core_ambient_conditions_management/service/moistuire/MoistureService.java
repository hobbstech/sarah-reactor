package io.github.hobbstech.sarah_core_ambient_conditions_management.service.moistuire;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.MoistureRecord;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.MoistureDto;

public interface MoistureService {

    MoistureRecord saveMoisture(MoistureDto MoistureDto);

}
