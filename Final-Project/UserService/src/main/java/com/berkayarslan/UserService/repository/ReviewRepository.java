package com.berkayarslan.UserService.repository;

import com.berkayarslan.UserService.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findAllByRestaurantId(String id);

    List<Review> findAllByUser_Id(Long id);
}
