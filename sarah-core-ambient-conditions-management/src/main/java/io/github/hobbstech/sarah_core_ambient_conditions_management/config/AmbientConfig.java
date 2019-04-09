package io.github.hobbstech.sarah_core_ambient_conditions_management.config;

import io.github.hobbstech.sarah_core_ambient_conditions_management.repository.RoomRepository;
import io.github.hobbstech.sarah_core_ambient_conditions_management.repository.TemperatureRecordRepository;
import io.github.hobbstech.sarah_core_ambient_conditions_management.repository.TemperatureSetpointRepository;
import io.github.hobbstech.sarah_core_ambient_conditions_management.service.*;
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

}
