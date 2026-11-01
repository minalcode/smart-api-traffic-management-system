package com.artificial.smart_api_traffic_management_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class TrafficConfig {

    public static final int MAX_REQUESTS = 5;

    public static final int TIME_WINDOW = 60;

    @Bean
    public ConcurrentHashMap<String, AtomicInteger> requestCounter() {
        return new ConcurrentHashMap<>();
    }
}