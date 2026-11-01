package com.artificial.smart_api_traffic_management_system.Service;

import com.artificial.smart_api_traffic_management_system.Model.ApiRequest;
import com.artificial.smart_api_traffic_management_system.Repository.ApiRequestRepository;
import com.artificial.smart_api_traffic_management_system.config.TrafficConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ApiTrafficService {

    private final ApiRequestRepository apiRequestRepository;

    private final ConcurrentHashMap<String, AtomicInteger> requestCounter;

    private final ConcurrentHashMap<String, LocalDateTime> requestTime =
            new ConcurrentHashMap<>();

    public ApiTrafficService(
            ApiRequestRepository apiRequestRepository,
            ConcurrentHashMap<String, AtomicInteger> requestCounter) {

        this.apiRequestRepository = apiRequestRepository;
        this.requestCounter = requestCounter;
    }

    public ApiRequest trackRequest(String apiKey, String endpoint, String httpMethod) {

        synchronized (requestCounter) {

            LocalDateTime now = LocalDateTime.now();

            LocalDateTime startTime = requestTime.get(apiKey);

            if (startTime == null ||
                    !now.isBefore(startTime.plusSeconds(TrafficConfig.TIME_WINDOW))) {

                requestCounter.put(apiKey, new AtomicInteger(0));
                requestTime.put(apiKey, now);
            }

            AtomicInteger counter = requestCounter.get(apiKey);

            boolean allowed = counter.get() < TrafficConfig.MAX_REQUESTS;

            if (allowed) {
                counter.incrementAndGet();
            }

            ApiRequest apiRequest = new ApiRequest();

            apiRequest.setApiKey(apiKey);
            apiRequest.setEndpoint(endpoint);
            apiRequest.setHttpMethod(httpMethod);
            apiRequest.setRequestTime(now);
            apiRequest.setAllowed(allowed);

            return apiRequestRepository.save(apiRequest);
        }
    }
}