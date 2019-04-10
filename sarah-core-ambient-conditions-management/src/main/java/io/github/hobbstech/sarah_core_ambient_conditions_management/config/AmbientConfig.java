package io.github.hobbstech.sarah_core_ambient_conditions_management.config;

import io.github.hobbstech.sarah_core_ambient_conditions_management.repository.*;
import io.github.hobbstech.sarah_core_ambient_conditions_management.service.*;
import io.github.hobbstech.sarah_core_ambient_conditions_management.service.flame.FlameActuationService;
import io.github.hobbstech.sarah_core_ambient_conditions_management.service.flame.FlameActuationServiceImpl;
import io.github.hobbstech.sarah_core_ambient_conditions_management.service.flame.FlameService;
import io.github.hobbstech.sarah_core_ambient_conditions_management.service.flame.FlameServiceImpl;
import io.github.hobbstech.sarah_core_ambient_conditions_management.service.gas.GasActuationService;
import io.github.hobbstech.sarah_core_ambient_conditions_management.service.gas.GasActuationServiceImpl;
import io.github.hobbstech.sarah_core_ambient_conditions_management.service.gas.GassesService;
import io.github.hobbstech.sarah_core_ambient_conditions_management.service.gas.GassesServiceImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "io.github.hobbstech.sarah_core_ambient_conditions_management")
@EntityScan(basePackages = "io.github.hobbstech.sarah_core_ambient_conditions_management")
@EnableJpaRepositories(basePackages = "io.github.hobbstech.sarah_core_ambient_conditions_management")
public class AmbientConfig {

    @Bean
    public RoomService roomService(RoomRepository roomRepository) {
        return new RoomServiceImpl(roomRepository);
    }

    @Bean
    public TemperatureRecordService temperatureRecordService(TemperatureRecordRepository temperatureRecordRepository,
                                                             RoomRepository roomRepository) {
        return new TemperatureRecordServiceImpl(temperatureRecordRepository, roomRepository);
    }

    @Bean
    public TemperatureRegulationService temperatureRegulationService(TemperatureSetpointRepository temperatureSetpointRepository,
                                                                     RoomService roomService) {
        return new TemperatureRegulationServiceImpl(temperatureSetpointRepository, roomService);
    }

    @Bean
    public GassesService gassesService(RoomService roomService, GasRecordRepository gasRecordRepository,
                                       GasActuationService gasActuationService) {
        return new GassesServiceImpl(gasRecordRepository, roomService, gasActuationService);
    }

    @Bean
    public GasActuationService gasActuationService() {
        return new GasActuationServiceImpl();
    }

    @Bean
    public FlameService flameService(RoomService roomService, FlameRecordRepository flameRecordRepository,
                                     FlameActuationService flameActuationService) {
        return new FlameServiceImpl(roomService, flameRecordRepository, flameActuationService);
    }

    @Bean
    public FlameActuationService flameActuationService() {
        return new FlameActuationServiceImpl();
    }

}