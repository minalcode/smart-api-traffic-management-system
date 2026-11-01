package com.artificial.smart_api_traffic_management_system.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "api_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String apiKey;

    private String endpoint;

    private String httpMethod;

    private LocalDateTime requestTime;

    private boolean allowed;
}