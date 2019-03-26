package io.github.hobbstech.weather_management.service.synchronization.config;

import io.github.hobbstech.weather_management.service.synchronization.DayWeatherUpdateJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import java.io.IOException;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Configuration
@ConditionalOnExpression("'${using.spring.schedulerFactory}'=='false'")
public class QuartzWeatherSyncConfig {

    private ApplicationContext applicationContext;

    public QuartzWeatherSyncConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

//    @Bean
//    public DayWeatherUpdateJob dayWeatherUpdateJob(WeatherForTheDayRepository weatherForTheDayRepository,
//                                                   WeatherForTheDayProvider weatherForTheDayProvider,
//                                                   ResidentialLocationRepository residentialLocationRepository){
//
//        return new DayWeatherUpdateJob(weatherForTheDayRepository, weatherForTheDayProvider, residentialLocationRepository);
//
//    }

    @Bean
    public JobDetail dayWeatherUpdateJobDetail() {
        return JobBuilder.newJob().ofType(DayWeatherUpdateJob.class)
                .storeDurably()
                .withIdentity("Day_Weather_Update_Job")
                .withDescription("Synchronize day weather updates for the specified location")
                .build();
    }

    @Bean
    public Trigger dayWeatherUpdateJobTrigger(JobDetail dayWeatherUpdateJobDetail) {
        return TriggerBuilder.newTrigger().forJob(dayWeatherUpdateJobDetail)
                .withIdentity("Day_Weather_Update_Job_Trigger")
                .withDescription("Trigger for day weather synchronization job update")
                .withSchedule(simpleSchedule().withIntervalInMilliseconds(10000).repeatForever()).build();
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public Scheduler scheduler(Trigger trigger, JobDetail job) throws IOException, SchedulerException {
        StdSchedulerFactory factory = new StdSchedulerFactory();
        factory.initialize(new ClassPathResource("quartz.properties").getInputStream());

        Scheduler scheduler = factory.getScheduler();
        scheduler.setJobFactory(springBeanJobFactory());
        scheduler.scheduleJob(job, trigger);

        scheduler.start();
        return scheduler;
    }

}
