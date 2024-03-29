package com.berkayarslan.UserService.client;


import com.berkayarslan.UserService.dto.ReviewScoreDTO;
import com.berkayarslan.UserService.dto.ReviewUpdateScoreDTO;
import com.berkayarslan.UserService.general.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "restaurant", url = "http://172.28.0.10:8080/api/v1/restaurants")
public interface RestaurantClient {

    @PostMapping("/score/{id}")
    ResponseEntity<RestResponse<Boolean>> sendRestaurantScores(@PathVariable String id, @RequestBody ReviewScoreDTO reviewScoreDTO);

    @PutMapping("/score/{id}")
    ResponseEntity<RestResponse<Boolean>> sendUpdateRestaurantReview(@PathVariable String id, @RequestBody ReviewUpdateScoreDTO reviewUpdateScoreDTO);
}
