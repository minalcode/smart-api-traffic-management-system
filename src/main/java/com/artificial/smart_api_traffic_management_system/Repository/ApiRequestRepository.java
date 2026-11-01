package com.artificial.smart_api_traffic_management_system.Repository;

import com.artificial.smart_api_traffic_management_system.Model.ApiRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiRequestRepository extends JpaRepository<ApiRequest, Long> {

}