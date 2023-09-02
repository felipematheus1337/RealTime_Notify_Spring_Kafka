package com.request.request.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableAsync
public class CustomConfigurations {

    @Bean
    public Executor asyncExecutor() {
        int availableProcessors = getAvailableProcessors();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(availableProcessors);
        executor.setMaxPoolSize(availableProcessors * 2);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("async-executor");
        executor.initialize();
        return executor;
    }
    private int getAvailableProcessors() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        return osBean.getAvailableProcessors();
    }

}
