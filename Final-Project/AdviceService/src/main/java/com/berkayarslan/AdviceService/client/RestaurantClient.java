package com.berkayarslan.AdviceService.client;

import com.berkayarslan.AdviceService.dto.RestaurantInfoDTO;
import com.berkayarslan.AdviceService.response.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "restaurant",url = "http://172.28.0.10:8080/api/v1/restaurants")
public interface RestaurantClient {



    @GetMapping("/nearby")
    ResponseEntity<RestResponse<List<RestaurantInfoDTO>>> getNearbyRestaurants(@RequestParam Double latitude, @RequestParam Double longitude);
}
