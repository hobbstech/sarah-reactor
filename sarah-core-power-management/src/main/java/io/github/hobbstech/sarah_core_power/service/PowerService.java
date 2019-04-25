package io.github.hobbstech.sarah_core_power.service;

import io.github.hobbstech.sarah_core_power.dto.PowerRecordDto;
import io.github.hobbstech.sarah_core_power.model.PowerRecord;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;

public interface PowerService {

    PowerRecord savePowerRecord(PowerRecordDto powerRecordDto);

    Page<PowerRecord> getPowerRecords(Integer page, Integer size, Long roomId);

    @Scheduled(fixedRate = 1000/*86400000*/)
    void calculateDailyPowerUsage();

}
