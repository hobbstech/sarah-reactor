package io.github.hobbstech.sarah_core_power.service;

import io.github.hobbstech.sarah_core_ambient_conditions_management.service.RoomService;
import io.github.hobbstech.sarah_core_power.dto.PowerRecordDto;
import io.github.hobbstech.sarah_core_power.model.PowerRecord;
import io.github.hobbstech.sarah_core_power.model.PowerUtilizationRecord;
import io.github.hobbstech.sarah_core_power.repository.PowerRecordRepository;
import io.github.hobbstech.sarah_core_power.repository.PowerUtilizationRecordRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.groupingBy;

@Slf4j
public class PowerServiceImpl implements PowerService {

    private final PowerRecordRepository powerRecordRepository;

    private final RoomService roomService;

    private final PowerUtilizationRecordRepository powerUtilizationRecordRepository;

    public PowerServiceImpl(PowerRecordRepository powerRecordRepository,
                            RoomService roomService, PowerUtilizationRecordRepository powerUtilizationRecordRepository) {
        this.powerRecordRepository = powerRecordRepository;
        this.roomService = roomService;
        this.powerUtilizationRecordRepository = powerUtilizationRecordRepository;
    }

    @Override
    public PowerRecord savePowerRecord(PowerRecordDto powerRecordDto) {
        log.info("---> Saving power record : {}", powerRecordDto);
        requireNonNull(powerRecordDto, "Power dto must not be null");
        val room = roomService.findById(powerRecordDto.getRoomId());
        val powerRecord = PowerRecord.builder()
                .power(powerRecordDto.getPower())
                .room(room)
                .build();
        return powerRecordRepository.save(powerRecord);
    }

    @Override
    public Page<PowerRecord> getPowerRecords(Integer page, Integer size, Long roomId) {
        return powerRecordRepository.findAllByRoomId(PageRequest.of(page, size), roomId);
    }

    @Override
    public void calculateDailyPowerUsage() {
        log.info("---> Updating power utilization for rooms");
        val startDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().minusHours(24);
        val roomPowerRecords = powerRecordRepository.
                findAllByDateCreatedAfter(Timestamp.valueOf(startDate))
                .stream().collect(groupingBy(PowerRecord::getRoom));

        val powerUtilizationRecords = new HashSet<PowerUtilizationRecord>();

        roomPowerRecords.forEach((room, powerRecords) -> {
            val averagePower = powerRecords.parallelStream().mapToDouble(PowerRecord::getPower)
                    .average().orElse(0);

            val kiloWattHourDayUsage = averagePower * 24;

            val powerUtilization = PowerUtilizationRecord.builder()
                    .dayAvgPowerUsage(kiloWattHourDayUsage)
                    .recordedAt(new Date())
                    .room(room)
                    .build();

            powerUtilizationRecords.add(powerUtilization);

            log.info("---> Room : {} power utilization : {}", room.getId(), powerUtilization);
        });

        powerUtilizationRecordRepository.saveAll(powerUtilizationRecords);

    }
}
