package io.github.hobbstech.sara_api.config;

import io.github.hobbstech.sarah_core_utils.core.AutoWiringSpringBeanJobFactory;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.annotation.PostConstruct;

@Configuration
@Slf4j
public class JobsConfigurations {

    @PostConstruct
    public void init() {
        log.info("---> Logging");
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory(ApplicationContext applicationContext) {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        log.debug("Configuring Job factory");

        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean scheduler(SpringBeanJobFactory springBeanJobFactory,
                                          Trigger dayWeatherUpdateJobTrigger,
                                          Trigger powerUtilizationTrigger,
                                          JobDetail dayWeatherUpdateJobDetail,
                                          JobDetail powerUtilizationJobDetail) {

        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));

        log.debug("Setting the Scheduler up");
        schedulerFactory.setJobFactory(springBeanJobFactory);
        schedulerFactory.setJobDetails(dayWeatherUpdateJobDetail, powerUtilizationJobDetail);
        schedulerFactory.setTriggers(dayWeatherUpdateJobTrigger, powerUtilizationTrigger);

        return schedulerFactory;
    }

}
