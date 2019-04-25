package io.github.hobbstech.sarah_core_power.config;

import io.github.hobbstech.sarah_core_power.service.PowerUtilizationUpdaterJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
@Slf4j
public class PowerUtilizationUpdaterJobConfig {

    @Value("${power-utilization-update-job.frequency-in-seconds}")
    private Integer frequencyInSeconds;

    @Bean
    public JobDetailFactoryBean powerUtilizationJobDetail() {

        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(PowerUtilizationUpdaterJob.class);
        jobDetailFactory.setName("Power_Utilization_Update_Job");
        jobDetailFactory.setDescription("Calculate power usage in KWH for a room");
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    @Bean
    public SimpleTriggerFactoryBean powerUtilizationTrigger(JobDetail powerUtilizationJobDetail) {

        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(powerUtilizationJobDetail);
        log.info("Configuring trigger to fire every {} seconds", frequencyInSeconds);

        trigger.setRepeatInterval(frequencyInSeconds * 1000L);
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        trigger.setName("Power_Utilization_Update_Job_Trigger");
        trigger.setDescription("Trigger for power utilization job update");
        return trigger;
    }

}
