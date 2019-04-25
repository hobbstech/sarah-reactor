package io.github.hobbstech.sara_api.power;

import io.github.hobbstech.sarah_core_power.dto.PowerRecordDto;
import io.github.hobbstech.sarah_core_power.model.PowerRecord;
import io.github.hobbstech.sarah_core_power.service.PowerService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
public class PowerManagementRestController {

    private final PowerService powerService;

    public PowerManagementRestController(PowerService powerService) {
        this.powerService = powerService;
    }

    @GetMapping("/v1/power-records")
    public Page<PowerRecord> getPowerRecordForRoom(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "100", required = false) Integer size,
            @RequestParam(value = "page") Long roomId) {

        return powerService.getPowerRecords(page, size, roomId);

    }

    @PostMapping("/v1/power-records")
    public PowerRecord savePowerRecord(@RequestBody PowerRecordDto powerRecordDto) {
        return powerService.savePowerRecord(powerRecordDto);
    }


}
