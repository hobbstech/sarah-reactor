package io.github.hobbstech.sarah_core_ambient_conditions_management.service.temprature;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.TemperatureRecord;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.TemperatureDto;
import io.github.hobbstech.sarah_core_ambient_conditions_management.repository.RoomRepository;
import io.github.hobbstech.sarah_core_ambient_conditions_management.repository.TemperatureRecordRepository;
import io.github.hobbstech.sarah_core_utils.exceptions.RecordNotFoundException;
import lombok.val;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;

import static java.util.Comparator.comparing;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class TemperatureRecordServiceImpl implements TemperatureRecordService {

    private final TemperatureRecordRepository temperatureRecordRepository;

    private final RoomRepository roomRepository;

    public TemperatureRecordServiceImpl(TemperatureRecordRepository temperatureRecordRepository,
                                        RoomRepository roomRepository) {
        this.temperatureRecordRepository = temperatureRecordRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public TemperatureRecord saveTemperature(final TemperatureDto temperatureDto) {
        val temperatureRecord = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .convertValue(temperatureDto, TemperatureRecord.class);
        temperatureRecord.setMaxTemp(temperatureDto.getTemperature());
        temperatureRecord.setMaxTempTime(new Date());
        temperatureRecord.setMinTemp(temperatureDto.getTemperature());
        temperatureRecord.setMinTempTime(new Date());

        val tempRecords = temperatureRecordRepository.findAllByDate(LocalDate.now());

        tempRecords.stream()
                .max(comparing(TemperatureRecord::getTemperature))
                .ifPresent(tempRecord -> {
                    if (tempRecord.getTemperature() < temperatureDto.getTemperature()) {
                        temperatureRecord.setMaxTemp(temperatureDto.getTemperature());
                        temperatureRecord.setMaxTempTime(new Date());
                    } else {
                        temperatureRecord.setMaxTemp(tempRecord.getTemperature());
                        temperatureRecord.setMaxTempTime(tempRecord.getMinTempTime());
                    }
                });

        tempRecords.stream()
                .min(comparing(TemperatureRecord::getTemperature))
                .ifPresent(tempRecord -> {
                    if (tempRecord.getTemperature() > temperatureDto.getTemperature()) {
                        temperatureRecord.setMinTemp(temperatureDto.getTemperature());
                        temperatureRecord.setMinTempTime(new Date());
                    } else {
                        temperatureRecord.setMinTemp(tempRecord.getTemperature());
                        temperatureRecord.setMinTempTime(tempRecord.getMinTempTime());
                    }
                });

        val roomId = temperatureDto.getRoomId();

        val room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RecordNotFoundException("Room was not found"));

        temperatureRecord.setRoom(room);

        return temperatureRecordRepository.save(temperatureRecord);
    }

    @Override
    public Collection<TemperatureRecord> getTemperatureRecordsPerDay(Date date) {
        requireNonNull(date);
        return temperatureRecordRepository
                .findAllByDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .stream()
                .sorted(comparing(TemperatureRecord::getDateCreated))
                .collect(toList());
    }

    @Override
    public Collection<TemperatureRecord> getTemperatureRecordsPerDayForRoom(Date date, Long roomId) {

        val room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RecordNotFoundException("Room was not found"));

        return temperatureRecordRepository.findAllByDateAndRoom(
                date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), room).stream()
                .sorted(comparing(TemperatureRecord::getDateCreated))
                .collect(toList());
    }
}
