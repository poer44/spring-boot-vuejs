package com.meiya.miamodel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

/**
 * @author xuebw
 */
@Configuration
@EnableAsync
public class ExecutorConfig {

    @Bean
    public Executor executor() {
        ForkJoinPool executor = new ForkJoinPool();
        return executor;
    }
}
