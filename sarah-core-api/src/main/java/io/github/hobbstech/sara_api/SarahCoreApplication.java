package io.github.hobbstech.sara_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableFeignClients
@ComponentScan(basePackages = "io.github.hobbstech")
public class SarahCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SarahCoreApplication.class, args);
    }

}
