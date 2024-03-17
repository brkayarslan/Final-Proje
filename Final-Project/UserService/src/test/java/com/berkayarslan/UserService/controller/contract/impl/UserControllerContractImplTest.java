package com.berkayarslan.UserService.controller.contract.impl;

import com.berkayarslan.UserService.dto.UserDTO;
import com.berkayarslan.UserService.dto.UserLocationDTO;
import com.berkayarslan.UserService.mapper.UserMapper;
import com.berkayarslan.UserService.model.User;
import com.berkayarslan.UserService.request.UserSaveRequest;
import com.berkayarslan.UserService.request.UserUpdateRequest;
import com.berkayarslan.UserService.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerContractImplTest {

    @InjectMocks
    private UserControllerContractImpl userControllerContract;

    @Mock
    private UserService userService;


    @Test
    void saveUser() {
        UserSaveRequest userSaveRequest = new UserSaveRequest("firstName",
                                                              "lastName",
                                                              "0123456789",
                                                              "example@gmail.com",
                                                              "987456123",
                                                              12.123456,
                                                              12.123456);
        User user = new User("firstName",
                             "lastName",
                             "0123456789",
                             "example@gmail.com",
                             "987456123",
                             12.123456,
                             12.123456);
        User savedUser = new User(12L,
                                  "firstName",
                                  "lastName",
                                  "0123456789",
                                  "example@gmail.com",
                                  "987456123",
                                  12.123456,
                                  12.123456);



        when(userService.save(user)).thenReturn(savedUser);

        UserDTO result = userControllerContract.saveUser(userSaveRequest);

        assertNotNull(result);
        verify(userService).save(user);
    }

    @Test
    void shouldFindAllUsers() {

        List<User> users = Arrays.asList(new User(12L,
                                                  "firstName",
                                                  "lastName",
                                                  "0123456789",
                                                  "example@gmail.com",
                                                  "987456123",
                                                  12.123456,
                                                  12.123456));
        List<UserDTO> userDTOs = Arrays.asList(new UserDTO(12L,
                                                           "firstName",
                                                           "lastName",
                                                           "0123456789",
                                                           "example@gmail.com",
                                                           "987456123",
                                                           12.123456,
                                                           12.123456));

        when(userService.findAll()).thenReturn(users);


        List<UserDTO> result = userControllerContract.findAllUsers();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(userService).findAll();


    }

    @Test
    void shouldGetUserById() {

        User user = new User();
        UserDTO userDTO = new UserDTO(12L,
                                      "firstName",
                                      "lastName",
                                      "0123456789",
                                      "example@gmail.com",
                                      "987456123",
                                      12.123456,
                                      12.123456);

        when(userService.findByIdWithControl(1L)).thenReturn(user);

        UserDTO result = userControllerContract.getUserById(1L);

        assertNotNull(result);
        verify(userService).findByIdWithControl(1L);
    }

    @Test
    void shouldGetUserLocationById() {
        User user = new User(12L, "firstName", "lastName", "0123456789", "example@gmail.com", "987456123", 12.123456, 12.123456);

        when(userService.findByIdWithControl(1L)).thenReturn(user);

        UserLocationDTO result = userControllerContract.getUserLocationById(1L);

        assertNotNull(result);
        assertEquals(12.123456, result.latitude());
        assertEquals(12.123456, result.longitude());
    }


    @Test
    void shouldUpdateUser() {

        UserUpdateRequest updateRequest = new UserUpdateRequest(1L,
                                                                "firstName",
                                                                "lastName",
                                                                "0123456789",
                                                                "example@gmail.com",
                                                                "987456123",
                                                                12.123456,
                                                                12.123456);

        User userBeforeUpdate = new User(1L,
                                         "Name",
                                         "SurName",
                                         "0123456789",
                                         "example@gmail.com",
                                         "987456123",
                                         12.123456,
                                         12.123456);

        User userAfterUpdate = new User(1L,
                "firstName",
                "lastName",
                "0123456789",
                "example@gmail.com",
                "987456123",
                12.123456,
                12.123456);

        UserDTO expectedUserDTO = new UserDTO(1L,
                "firstName",
                "lastName",
                "0123456789",
                "example@gmail.com",
                "987456123",
                12.123456,
                12.123456);

        when(userService.findByIdWithControl(1L)).thenReturn(userBeforeUpdate);
        when(userService.save(userBeforeUpdate)).thenReturn(userAfterUpdate);

        UserDTO resultUserDTO = userControllerContract.updateUser(updateRequest);

        assertNotNull(resultUserDTO);

        verify(userService).findByIdWithControl(updateRequest.id());
        verify(userService).save(userBeforeUpdate);

    }

}