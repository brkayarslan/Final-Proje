package com.berkayarslan.UserService.model;

import com.berkayarslan.UserService.general.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity
@Table(name = "REVIEW_TABLE")
public class Review extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    private Long restaurantId;

    @Min(1)
    @Max(5)
    private Integer foodScore;

    @Min(1)
    @Max(5)
    private Integer presentationScore;

    @Min(1)
    @Max(5)
    private Integer deliveryScore;

    private String comment;

    private LocalDate reviewDate;

    public Review(User user, Long restaurantId, Integer foodScore, Integer presentationScore, Integer deliveryScore, String comment, LocalDate reviewDate) {
        this.user = user;
        this.restaurantId = restaurantId;
        this.foodScore = foodScore;
        this.presentationScore = presentationScore;
        this.deliveryScore = deliveryScore;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }
}
