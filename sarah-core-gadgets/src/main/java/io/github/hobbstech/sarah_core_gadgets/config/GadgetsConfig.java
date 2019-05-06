package io.github.hobbstech.sarah_core_gadgets.config;

import io.github.hobbstech.sarah_core_gadgets.repository.GadgetRepository;
import io.github.hobbstech.sarah_core_gadgets.repository.RelayRepository;
import io.github.hobbstech.sarah_core_gadgets.service.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "io.github.hobbstech.sarah_core_gadgets.model")
@EnableJpaRepositories(basePackages = "io.github.hobbstech.sarah_core_gadgets.repository")
@ComponentScan(basePackages = "io.github.hobbstech.sarah_core_gadgets")
@EnableFeignClients(basePackages = "io.github.hobbstech.sarah_core_gadgets")
public class GadgetsConfig {

    @Bean
    public GadgetService gadgetService(GadgetRepository gadgetRepository, RelayService relayRepository) {
        return new GadgetServiceImpl(gadgetRepository, relayRepository);
    }

    @Bean
    public RelayService relayService(RelayRepository relayRepository) {
        return new RelayServiceImpl(relayRepository);
    }

    @Bean
    public GadgetActuatorService gadgetActuatorService(GadgetRepository gadgetRepository, RelayServer relayServer) {
        return new GadgetActuatorServiceImpl(gadgetRepository, relayServer);
    }

}
