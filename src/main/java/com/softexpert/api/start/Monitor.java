package com.softexpert.api.start;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.softexpert.api.start.jobs.MonitorJob;

@Component
public class Monitor implements ApplicationListener<ApplicationReadyEvent>{

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		
		Trigger trigger = TriggerBuilder
		.newTrigger()
		.withIdentity("AÇÕES", "GROUP")
		.withDescription("COMPRA E VENDA DE AÇÕES")
		.withSchedule(
				SimpleScheduleBuilder
				.simpleSchedule()
				.withIntervalInSeconds(5)
				.repeatForever()
				).build();
		
		JobDetail monitorJob = JobBuilder.newJob(MonitorJob.class)
				.withIdentity("JOB AÇÕES", "GROUP")
				.withDescription("JOB")
				.build();
		
		try {
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(monitorJob, trigger);
		} catch (SchedulerException e) {
			
			e.printStackTrace();
		}
	}

}