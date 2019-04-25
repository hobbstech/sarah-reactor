package io.github.hobbstech.weather_management.service.synchronization.config;

import io.github.hobbstech.weather_management.service.synchronization.DayWeatherUpdateJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Slf4j
@Configuration
@ConditionalOnExpression("'${using.spring.schedulerFactory}'=='true'")
public class SpringQuartzWeatherSyncConfig {

    @Value("${weather-update-job.frequency-in-seconds}")
    private Integer frequencyInSeconds;

    @Bean
    public JobDetailFactoryBean dayWeatherUpdateJobDetail() {

        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(DayWeatherUpdateJob.class);
        jobDetailFactory.setName("Day_Weather_Update_Job");
        jobDetailFactory.setDescription("Synchronize day weather updates for the specified location");
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    @Bean
    public SimpleTriggerFactoryBean dayWeatherUpdateJobTrigger(JobDetail dayWeatherUpdateJobDetail) {

        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(dayWeatherUpdateJobDetail);
        log.info("Configuring trigger to fire every {} seconds", frequencyInSeconds);

        trigger.setRepeatInterval(frequencyInSeconds * 1000);
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        trigger.setName("Day_Weather_Update_Job_Trigger");
        trigger.setDescription("Trigger for day weather synchronization job update");
        return trigger;
    }

}
