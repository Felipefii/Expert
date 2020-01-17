package com.softexpert.api.controllers;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softexpert.api.jobs.MonitorJob;

@RestController
@RequestMapping("/monitor")
public class MonitorController {

	@GetMapping
	public void monitorar() {
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
