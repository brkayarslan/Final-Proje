package com.berkayarslan.UserService.controller;

import com.berkayarslan.UserService.UserServiceApplication;
import com.berkayarslan.UserService.controller.contract.ReviewControllerContract;
import com.berkayarslan.UserService.dto.ReviewDTO;
import com.berkayarslan.UserService.dto.ReviewUpdateScoreDTO;
import com.berkayarslan.UserService.model.Review;
import com.berkayarslan.UserService.model.User;
import com.berkayarslan.UserService.request.ReviewSaveRequest;
import com.berkayarslan.UserService.request.ReviewUpdateRequest;
import com.berkayarslan.UserService.service.ReviewScoreSenderService;
import com.berkayarslan.UserService.service.ReviewService;
import com.berkayarslan.UserService.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {UserServiceApplication.class})
class ReviewControllerTest extends BaseControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private ReviewScoreSenderService reviewScoreSenderService;

    @MockBean
    private ReviewService reviewService;

    @MockBean
    private ReviewControllerContract reviewControllerContract;

    @MockBean
    private UserService userService;
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        when(reviewScoreSenderService.sendReviewScoresToRestaurant(any(Review.class))).thenReturn(true);
    }
    @Test
    public void findAllReviews_ShouldReturnReviews() throws Exception {
        List<ReviewDTO> reviews = Arrays.asList(new ReviewDTO(1L,"user","9d7b6d21-3bc2-4513-b9a9-7ef900a60d96",5,4,2," Delicious meal, would recommend!", LocalDate.now()),
                                                new ReviewDTO(2L,"user","9d7b6d21-3bc2-4513-b9a9-7ef900a60d96",5,4,2," Delicious meal, would recommend!", LocalDate.now()));
        when(reviewControllerContract.findAllReviews()).thenReturn(reviews);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reviews/110"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldFindById() throws Exception {
        ReviewDTO reviews = new ReviewDTO(1L,"user","9d7b6d21-3bc2-4513-b9a9-7ef900a60d96",5,4,2," Delicious meal, would recommend!", LocalDate.now());
        when(reviewControllerContract.findReviewById(anyLong())).thenReturn(reviews);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reviews/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldFindByUserId() throws Exception {
        List<ReviewDTO> reviews = Arrays.asList(new ReviewDTO(1L,"user","9d7b6d21-3bc2-4513-b9a9-7ef900a60d96",5,4,2," Delicious meal, would recommend!", LocalDate.now()),
                                                new ReviewDTO(2L,"user","9d7b6d21-3bc2-4513-b9a9-7ef900a60d96",5,4,2," Delicious meal, would recommend!", LocalDate.now()));
        when(reviewControllerContract.findReviewsByUserId(anyLong())).thenReturn(reviews);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reviews/userId/20"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldFindByRestaurantId() throws Exception {
        List<ReviewDTO> reviews = Arrays.asList(new ReviewDTO(1L,"user","9d7b6d21-3bc2-4513-b9a9-7ef900a60d96",5,4,2," Delicious meal, would recommend!", LocalDate.now()),
                new ReviewDTO(2L,"user","9d7b6d21-3bc2-4513-b9a9-7ef900a60d96",5,4,2," Delicious meal, would recommend!", LocalDate.now()));
        when(reviewControllerContract.findReviewsByRestaurantId(anyString())).thenReturn(reviews);


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reviews/restaurantId/76c763ac-0c99-4714-884b-63272bfcea95"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldSaveReview() throws Exception {

        User mockUser = new User("John", "Doe", "1234567890", "john.doe@example.com", "password", 40.712776, -74.005974);


        ReviewSaveRequest reviewSaveRequest = new ReviewSaveRequest(
                28L,
                "9d7b6d21-3bc2-4513-b9a9-7ef900a60d96",
                5,
                4,
                2,
                "Delicious meal, would recommend!"
        );

        Review review = new Review(1L,mockUser,"9d7b6d21-3bc2-4513-b9a9-7ef900a60d96",5,4,2," Delicious meal, would recommend!", LocalDate.now());

        when(userService.findByIdWithControl(28L)).thenReturn(mockUser);
        when(reviewService.save(any(Review.class))).thenReturn(review);


        String reviewSaveRequestJson = objectMapper.writeValueAsString(reviewSaveRequest);


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewSaveRequestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();


        boolean success = isSuccess(mvcResult);
        assertTrue(success);


    }

    @Test
    void shouldUpdateReview() throws Exception {
        User mockUser = new User("John", "Doe", "1234567890", "john.doe@example.com", "password", 40.712776, -74.005974);
        Review review = new Review(1L,mockUser,"9d7b6d21-3bc2-4513-b9a9-7ef900a60d96",5,4,2," Delicious meal, would recommend!", LocalDate.now());

        ReviewUpdateRequest reviewUpdateRequest = new ReviewUpdateRequest(1L,3,2,1,"Delicious meal, would recommend!");

        when(reviewService.findByIdWithControl(anyLong())).thenReturn(review);
        when(reviewScoreSenderService.sendUpdateReviewScoreToRestaurant(anyString(),any(ReviewUpdateScoreDTO.class))).thenReturn(true);
        when(reviewService.save(any(Review.class))).thenReturn(review);


        String reviewSaveRequestJson = objectMapper.writeValueAsString(reviewUpdateRequest);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewSaveRequestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();


        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }
}