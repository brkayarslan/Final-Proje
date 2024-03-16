package com.berkayarslan.AdviceService.client;

import com.berkayarslan.AdviceService.response.RestResponse;
import com.berkayarslan.AdviceService.dto.UserLocationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user",url = "http://172.28.0.2:8081/api/v1/users")
public interface UserClient {

    @GetMapping("/location/{id}")
    ResponseEntity<RestResponse<UserLocationDTO>> getUserLocationById(@PathVariable Long id);
}
