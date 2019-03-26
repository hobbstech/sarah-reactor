package io.github.hobbstech.weather_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WeatherManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherManagementApplication.class, args);
    }

}
