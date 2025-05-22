package com.shreya.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.time.LocalDateTime;
import java.util.concurrent.Executor;

@Configuration
public class CustomScheduledTasks implements SchedulingConfigurer {

	@Autowired
	private ThreadPoolTaskScheduler poolScheduler;  // Use ThreadPoolTaskScheduler instead of generic Executor

	@Scheduled(fixedRate = 5000)
	public void scheduledTask() {
		System.out.println("Task executed at " + LocalDateTime.now() + " on thread " + Thread.currentThread().getName());
	}

	@Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
	public void scheduleTaskWithFixedDelay() {
		System.out.println("Fixed delay task executed at " + LocalDateTime.now() + " on thread " + Thread.currentThread().getName());
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(poolScheduler);
	}
}
