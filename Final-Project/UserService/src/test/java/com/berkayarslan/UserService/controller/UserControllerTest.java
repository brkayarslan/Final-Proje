//package com.berkayarslan.UserService.controller;
//
//import com.berkayarslan.UserService.UserServiceApplication;
//import com.berkayarslan.UserService.request.UserUpdateRequest;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest(classes = {UserServiceApplication.class})
//class UserControllerTest extends BaseControllerTest {
//
//    @Autowired
//    private WebApplicationContext context;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
//        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
//    }
//    @Test
//    void shouldFindAllUsers() throws Exception {
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//
//        boolean success = isSuccess(mvcResult);
//        assertTrue(success);
//    }
//
//    @Test
//    void shouldFindUserById() throws Exception {
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/35"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//
//        boolean success = isSuccess(mvcResult);
//        assertTrue(success);
//    }
//
//    @Test
//    void shouldFindUserLocationById() throws Exception {
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/35"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//
//        boolean success = isSuccess(mvcResult);
//        assertTrue(success);
//    }
//
//    @Test
//    void shouldSaveUser() throws Exception {
//        String requestAsString = "{\n"
//                + "  \"firstName\": \"John\",\n"
//                + "  \"lastName\": \"Wick\",\n"
//                + "  \"telephone\": \"05391234567\",\n"
//                + "  \"e_mail\": \"john@gmail.com\",\n"
//                + "  \"password\": \"123456\",\n"
//                + "  \"latitude\": 41.018988670236325,\n"
//                + "  \"longitude\": 29.003468091674247\n"
//                + "}";
//
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
//                                                                    .content(requestAsString)
//                                                                    .contentType(MediaType.APPLICATION_JSON))
//                                     .andExpect(MockMvcResultMatchers.status().isOk())
//                                     .andReturn();
//
//        boolean success = isSuccess(mvcResult);
//        assertTrue(success);
//    }
//
//    @Test
//    void shouldUpdateUser() throws Exception {
//
//        UserUpdateRequest updateRequest = new UserUpdateRequest(1L,"cristiano","ronaldo","05439876543","c7@gmail.com","123456",41.10322874011265, 28.990931170887592);
//        String requestAsString = objectMapper.writeValueAsString(updateRequest);
//
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users")
//                        .content(requestAsString)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//
//        boolean success = isSuccess(mvcResult);
//
//        assertTrue(success);
//    }
//}