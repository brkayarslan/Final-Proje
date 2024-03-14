package com.berkayarslan.AdviceService.controller;

import com.berkayarslan.AdviceService.dto.RestaurantInfoDTO;
import com.berkayarslan.AdviceService.dto.RestaurantResponseDTO;
import com.berkayarslan.AdviceService.dto.UserLocationDTO;
import com.berkayarslan.AdviceService.service.AdviceService;
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

    @GetMapping
    public List<RestaurantInfoDTO> getRestaurants(){
        return adviceService.getRestaurants();
    }

    @GetMapping("/{id}")
    public UserLocationDTO getUserLocation(@PathVariable Long id){
        return adviceService.getUserLocationById(id);
    }

    @GetMapping("/advice/userId/{id}")
    public List<RestaurantResponseDTO> getAdvisedRestaurants(@PathVariable Long id){
        return adviceService.getAdviceRestaurantsByUserId(id);
    }
}
