package com.artificial.smart_api_traffic_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiRequestDto {

    private String apiKey;

    private String endpoint;

    private String httpMethod;
}