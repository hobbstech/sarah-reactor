package io.github.hobbstech.sarah_core_entertainment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EntertainmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntertainmentApplication.class, args);
    }

}
