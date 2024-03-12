package com.berkayarslan.UserService.controller.contract.impl;

import com.berkayarslan.UserService.controller.contract.UserControllerContract;
import com.berkayarslan.UserService.dto.UserDTO;
import com.berkayarslan.UserService.mapper.UserMapper;
import com.berkayarslan.UserService.model.User;
import com.berkayarslan.UserService.request.UserSaveRequest;
import com.berkayarslan.UserService.request.UserUpdateRequest;
import com.berkayarslan.UserService.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserControllerContractImpl implements UserControllerContract {

    private final UserService userService;

    public UserControllerContractImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDTO saveUser(UserSaveRequest userSaveRequest) {
        User user = UserMapper.INSTANCE.convertToUser(userSaveRequest);
        user = userService.save(user);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> userList = userService.findAll();
        return UserMapper.INSTANCE.convertToUserDTOList(userList);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userService.findByIdWithControl(id);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public UserDTO updateUser(UserUpdateRequest userUpdateRequest) {
        User user = userService.findByIdWithControl(userUpdateRequest.id());
        UserMapper.INSTANCE.updateUserFields(user,userUpdateRequest);
        userService.save(user);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public void deleteUser(Long id) {
        userService.delete(id);
    }
}
