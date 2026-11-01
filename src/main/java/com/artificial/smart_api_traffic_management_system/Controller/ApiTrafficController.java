package com.artificial.smart_api_traffic_management_system.Controller;

import com.artificial.smart_api_traffic_management_system.Model.ApiRequest;
import com.artificial.smart_api_traffic_management_system.Service.ApiTrafficService;
import com.artificial.smart_api_traffic_management_system.dto.ApiRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/traffic")
public class ApiTrafficController {

    private final ApiTrafficService apiTrafficService;

    public ApiTrafficController(ApiTrafficService apiTrafficService) {
        this.apiTrafficService = apiTrafficService;
    }

    @PostMapping("/check")
    public ResponseEntity<ApiRequest> checkTraffic(@RequestBody ApiRequestDto dto) {

        ApiRequest response = apiTrafficService.trackRequest(
                dto.getApiKey(),
                dto.getEndpoint(),
                dto.getHttpMethod());

        if (!response.isAllowed()) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body(response);
        }

        return ResponseEntity.ok(response);
    }
}