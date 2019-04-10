package io.github.hobbstech.sarah_core_ambient_conditions_management.service.gas;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.hobbstech.sarah_core_ambient_conditions_management.domain.GasRecord;
import io.github.hobbstech.sarah_core_ambient_conditions_management.dto.GassesDto;
import io.github.hobbstech.sarah_core_ambient_conditions_management.repository.GasRecordRepository;
import io.github.hobbstech.sarah_core_ambient_conditions_management.service.RoomService;
import lombok.val;

public class GassesServiceImpl implements GassesService {

    private final GasRecordRepository gasRecordRepository;

    private final RoomService roomService;

    private final GasActuationService gasActuationService;

    public GassesServiceImpl(GasRecordRepository gasRecordRepository, RoomService roomService, GasActuationService gasActuationService) {
        this.gasRecordRepository = gasRecordRepository;
        this.roomService = roomService;
        this.gasActuationService = gasActuationService;
    }

    @Override
    public GasRecord saveGasRecord(GassesDto gassesDto) {

        val gasRecord = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .convertValue(gassesDto, GasRecord.class);

        val typeOfGas = GasDeterminer.resolveGas(gassesDto.getGasValue());

        gasRecord.setTypeOfGas(typeOfGas);
        gasRecord.setRoom(roomService.findById(gassesDto.getRoomId()));

        val persistedGasRecord = gasRecordRepository.save(gasRecord);

        gasActuationService.actuateGases(persistedGasRecord);

        return persistedGasRecord;
    }
}
