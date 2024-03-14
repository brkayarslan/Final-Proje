package com.berkayarslan.AdviceService.client;

import com.berkayarslan.AdviceService.dto.RestaurantInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "restaurant",url = "http://localhost:8080/api/v1/restaurants")
public interface RestaurantClient {

    @GetMapping
    List<RestaurantInfoDTO> getRestaurants();

    @GetMapping("/nearby")
    List<RestaurantInfoDTO> getNearbyRestaurants(@RequestParam Double latitude, @RequestParam Double longitude);
}
