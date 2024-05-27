package com.mengka.springboot.config;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.*;

@Configuration
public class EventBusConfig {

    private static final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);

    @Bean
    public EventBus syncEventBus() {
        return new EventBus();
    }

    @Bean
    public AsyncEventBus asyncEventBus() {
        return new AsyncEventBus(new ThreadPoolExecutor(10, 600, 30,
                TimeUnit.SECONDS, queue, Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy()));
    }

}