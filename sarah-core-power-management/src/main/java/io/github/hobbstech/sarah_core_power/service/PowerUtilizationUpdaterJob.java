package io.github.hobbstech.sarah_core_power.service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PowerUtilizationUpdaterJob implements Job {

    @Autowired
    private PowerService powerService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        powerService.calculateDailyPowerUsage();
    }

}
