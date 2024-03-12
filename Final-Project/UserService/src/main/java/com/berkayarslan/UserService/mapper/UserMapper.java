package com.berkayarslan.UserService.mapper;

import com.berkayarslan.UserService.dto.UserDTO;
import com.berkayarslan.UserService.model.User;
import com.berkayarslan.UserService.request.UserSaveRequest;
import com.berkayarslan.UserService.request.UserUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    User convertToUser(UserSaveRequest userSaveRequest);
    UserDTO convertToUserDTO(User user);

    List<UserDTO> convertToUserDTOList(List<User> userList);

    @Mapping(target = "id",ignore = true)
    void updateUserFields(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
