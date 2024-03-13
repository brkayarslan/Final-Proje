package com.berkayarslan.UserService.controller;

import com.berkayarslan.UserService.controller.contract.UserControllerContract;
import com.berkayarslan.UserService.dto.UserDTO;
import com.berkayarslan.UserService.dto.UserLocationDTO;
import com.berkayarslan.UserService.general.RestResponse;
import com.berkayarslan.UserService.request.UserSaveRequest;
import com.berkayarslan.UserService.request.UserUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserControllerContract userControllerContract;

    public UserController(UserControllerContract userControllerContract) {
        this.userControllerContract = userControllerContract;
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<UserDTO>>> findAllUsers(){
        return ResponseEntity.ok(RestResponse.of(userControllerContract.findAllUsers()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserDTO>> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(RestResponse.of(userControllerContract.getUserById(id)));
    }

    @GetMapping("/location/{id}")
    public ResponseEntity<RestResponse<UserLocationDTO>> findUserLocationById(@PathVariable Long id){
        return ResponseEntity.ok(RestResponse.of(userControllerContract.getUserLocationById(id)));
    }

    @PostMapping
    public ResponseEntity<RestResponse<UserDTO>> saveUser(@RequestBody UserSaveRequest saveRequest){
        return ResponseEntity.ok(RestResponse.of(userControllerContract.saveUser(saveRequest)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<UserDTO>> updateUser(@RequestBody UserUpdateRequest updateRequest){
        return ResponseEntity.ok(RestResponse.of(userControllerContract.updateUser(updateRequest)));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userControllerContract.deleteUser(id);
    }
}
