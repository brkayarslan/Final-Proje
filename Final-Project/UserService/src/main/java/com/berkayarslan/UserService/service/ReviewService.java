package com.berkayarslan.UserService.service;

import com.berkayarslan.UserService.general.BaseEntityService;
import com.berkayarslan.UserService.model.Review;
import com.berkayarslan.UserService.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService extends BaseEntityService<Review, ReviewRepository> {
    protected ReviewService(ReviewRepository repository) {
        super(repository);
    }

    public List<Review> findAllByRestaurantId(String id){
        return getRepository().findAllByRestaurantId(id);
    }

    public List<Review> findAllByUserId(Long id){
        return getRepository().findAllByUser_Id(id);
    }


}
