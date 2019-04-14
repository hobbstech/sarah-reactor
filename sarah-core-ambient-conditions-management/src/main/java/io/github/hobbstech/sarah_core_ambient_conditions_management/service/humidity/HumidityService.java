package io.github.hobbstech.sarah_core_ambient_conditions_management.service.humidity;

import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.HumidityRecord;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.HumidityDto;

public interface HumidityService {

    HumidityRecord saveHumidity(HumidityDto humidityDto);

}
