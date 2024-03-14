package com.berkayarslan.AdviceService.controller;

import com.berkayarslan.AdviceService.response.RestResponse;
import com.berkayarslan.AdviceService.dto.RestaurantInfoDTO;
import com.berkayarslan.AdviceService.dto.RestaurantResponseDTO;
import com.berkayarslan.AdviceService.dto.UserLocationDTO;
import com.berkayarslan.AdviceService.service.AdviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/advices")
public class AdviceController {

    private final AdviceService adviceService;

    public AdviceController(AdviceService adviceService) {
        this.adviceService = adviceService;
    }

    @GetMapping("/userId/{id}")
    public ResponseEntity<RestResponse<List<RestaurantResponseDTO>>> getAdvisedRestaurants(@PathVariable Long id){
        return ResponseEntity.ok(RestResponse.of(adviceService.getAdviceRestaurantsByUserId(id)));
    }
}
