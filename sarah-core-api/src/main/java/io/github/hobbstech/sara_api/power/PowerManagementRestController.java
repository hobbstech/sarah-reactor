package io.github.hobbstech.sara_api.power;

import io.github.hobbstech.sarah_core_power.dto.PowerRecordDto;
import io.github.hobbstech.sarah_core_power.model.PowerRecord;
import io.github.hobbstech.sarah_core_power.model.PowerUtilizationRecord;
import io.github.hobbstech.sarah_core_power.repository.PowerUtilizationRecordRepository;
import io.github.hobbstech.sarah_core_power.service.PowerService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
public class PowerManagementRestController {

    private final PowerService powerService;

    private final PowerUtilizationRecordRepository powerUtilizationRecordRepository;

    public PowerManagementRestController(PowerService powerService, PowerUtilizationRecordRepository powerUtilizationRecordRepository) {
        this.powerService = powerService;
        this.powerUtilizationRecordRepository = powerUtilizationRecordRepository;
    }

    @GetMapping("/v1/power-records")
    public Page<PowerRecord> getPowerRecordForRoom(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "100", required = false) Integer size,
            @RequestParam(value = "roomId") Long roomId) {

        return powerService.getPowerRecords(page, size, roomId);

    }

    @PostMapping("/v1/power-records")
    public PowerRecord savePowerRecord(@RequestBody PowerRecordDto powerRecordDto) {
        return powerService.savePowerRecord(powerRecordDto);
    }

    @GetMapping("/v1/power-utilization")
    public PowerUtilizationRecord getUtilizationRecord(@RequestParam("roomId") Long roomId) {
        return powerUtilizationRecordRepository.findTop1ByRoom_IdOrderByDateCreatedDesc(roomId);
    }


}
