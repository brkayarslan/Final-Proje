package com.berkayarslan.UserService.controller.contract;

import com.berkayarslan.UserService.dto.UserDTO;
import com.berkayarslan.UserService.request.UserSaveRequest;
import com.berkayarslan.UserService.request.UserUpdateRequest;

import java.util.List;

public interface UserControllerContract {

    UserDTO saveUser(UserSaveRequest userSaveRequest);
    List<UserDTO> findAllUsers();
    UserDTO getUserById(Long id);
    UserDTO updateUser(UserUpdateRequest userUpdateRequest);
    void deleteUser(Long id);
}
