package ru.stm.lot4.receiver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Configuration
public class ExecutorConfiguration {
    @Bean
    public ScheduledExecutorService scheduledThreadPoolExecutor() {
        return new ScheduledThreadPoolExecutor(2);
    }
}
