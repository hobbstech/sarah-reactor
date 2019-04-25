package io.github.hobbstech.sarah_core_power.config;

import io.github.hobbstech.sarah_core_ambient_conditions_management.service.RoomService;
import io.github.hobbstech.sarah_core_power.repository.PowerRecordRepository;
import io.github.hobbstech.sarah_core_power.repository.PowerUtilizationRecordRepository;
import io.github.hobbstech.sarah_core_power.service.PowerService;
import io.github.hobbstech.sarah_core_power.service.PowerServiceImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableJpaRepositories(basePackages = "io.github.hobbstech.sarah_core_power.repository")
@EntityScan(basePackages = "io.github.hobbstech.sarah_core_power.model")
@EnableScheduling
public class PowerConfig {

    @Bean
    public PowerService powerService(PowerRecordRepository powerRecordRepository,
                                     RoomService roomService, PowerUtilizationRecordRepository powerUtilizationRecordRepository) {
        return new PowerServiceImpl(powerRecordRepository, roomService, powerUtilizationRecordRepository);
    }

}
