package io.github.hobbstech.weather_management.service.synchronization;

import io.github.hobbstech.weather_management.service.WeatherForTheDayProvider;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DayWeatherUpdateJob implements Job {

    @Autowired
    private WeatherForTheDayProvider weatherForTheDayProvider;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        log.info("---> Updating weather records");

        weatherForTheDayProvider.getWeatherForTodayForMyLocation();

    }

}
