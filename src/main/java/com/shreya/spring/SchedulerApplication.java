package com.shreya.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class SchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulerApplication.class, args);
    }

    // Define scheduler bean with thread pool
    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(5);
        scheduler.setThreadNamePrefix("custom-scheduler-");
        scheduler.initialize();  // very important
        return scheduler;
    }

    // Scheduled task example
    @Scheduled(fixedRate = 5000)
    public void logCurrentTime() {
        System.out.println("Current time: " + new Date() + " on thread " + Thread.currentThread().getName());
    }
}
