package com.berkayarslan.UserService.model;

import com.berkayarslan.UserService.general.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "RESTAURANT_ID")
    private String restaurantId;

    @Min(1)
    @Max(5)
    @Column(name = "FOOD_SCORE")
    private Integer foodScore;

    @Min(1)
    @Max(5)
    @Column(name = "PRESENTATION_SCORE")
    private Integer presentationScore;

    @Min(1)
    @Max(5)
    @Column(name = "DELIVERY_SCORE")
    private Integer deliveryScore;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "REVIEW_DATE")
    private LocalDate reviewDate;

    public Review(User user, String restaurantId, Integer foodScore, Integer presentationScore, Integer deliveryScore, String comment, LocalDate reviewDate) {
        this.user = user;
        this.restaurantId = restaurantId;
        this.foodScore = foodScore;
        this.presentationScore = presentationScore;
        this.deliveryScore = deliveryScore;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }
}
