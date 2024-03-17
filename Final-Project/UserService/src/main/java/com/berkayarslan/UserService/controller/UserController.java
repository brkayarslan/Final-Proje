package com.berkayarslan.UserService.controller;

import com.berkayarslan.UserService.controller.contract.UserControllerContract;
import com.berkayarslan.UserService.dto.UserDTO;
import com.berkayarslan.UserService.dto.UserLocationDTO;
import com.berkayarslan.UserService.general.RestResponse;
import com.berkayarslan.UserService.request.UserSaveRequest;
import com.berkayarslan.UserService.request.UserUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Validated
@Tag(name = "User Controller", description = "Provides endpoints for managing users, including operations for retrieving, creating, updating, and deleting user information.")
public class UserController {

    private final UserControllerContract userControllerContract;

    public UserController(UserControllerContract userControllerContract) {
        this.userControllerContract = userControllerContract;
    }

    @Operation(summary = "Get all users", description = "Retrieves all active users")
    @GetMapping
    public ResponseEntity<RestResponse<List<UserDTO>>> findAllUsers(){
        return ResponseEntity.ok(RestResponse.of(userControllerContract.findAllUsers()));
    }

    @Operation(summary = "Retrieves user by user id")
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserDTO>> findUserById(@PathVariable @Positive Long id){
        return ResponseEntity.ok(RestResponse.of(userControllerContract.getUserById(id)));
    }

    @Operation(summary = "Retrieves user location by user id")
    @GetMapping("/location/{id}")
    public ResponseEntity<RestResponse<UserLocationDTO>> findUserLocationById(@PathVariable @Positive Long id){
        return ResponseEntity.ok(RestResponse.of(userControllerContract.getUserLocationById(id)));
    }

    @Operation(
            description = "Creates new user",
            summary = "Create",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Users",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UserSaveRequest.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "new user",
                                                    summary = "Default",
                                                    description = "Complete request with all available fields for user",
                                                    value = "{\n"
                                                            + "  \"firstName\": \"John\",\n"
                                                            + "  \"lastName\": \"Smith\",\n"
                                                            + "  \"telephone\": \"5763333873\",\n"
                                                            + "  \"e_mail\": \"john@gmail.com\",\n"
                                                            + "  \"password\": \"123456\",\n"
                                                            + "  \"latitude\": \"41.02208020801947\",\n"
                                                            + "  \"longitude\": \"28.999552954557206\"\n"
                                                            + "}"
                                            )
                                    }
                            )
                    }
            )
    )
    @PostMapping
    public ResponseEntity<RestResponse<UserDTO>> saveUser(@RequestBody UserSaveRequest saveRequest){
        return ResponseEntity.ok(RestResponse.of(userControllerContract.saveUser(saveRequest)));
    }


    @Operation(
            description = "Updates old user",
            summary = "Update User",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Users",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UserUpdateRequest.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "update user",
                                                    summary = "Default",
                                                    description = "Complete request with all available fields for user",
                                                    value = "{\n"
                                                            + "  \"id\": \"40\",\n"
                                                            + "  \"firstName\": \"John\",\n"
                                                            + "  \"lastName\": \"Smith\",\n"
                                                            + "  \"telephone\": \"5763333873\",\n"
                                                            + "  \"e_mail\": \"john@gmail.com\",\n"
                                                            + "  \"password\": \"123456\",\n"
                                                            + "  \"latitude\": \"41.02208020801947\",\n"
                                                            + "  \"longitude\": \"28.999552954557206\"\n"
                                                            + "}"
                                            )
                                    }
                            )
                    }
            )
    )
    @PutMapping
    public ResponseEntity<RestResponse<UserDTO>> updateUser(@RequestBody UserUpdateRequest updateRequest){
        return ResponseEntity.ok(RestResponse.of(userControllerContract.updateUser(updateRequest)));
    }


    @Operation(summary = "Delete user by user id")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable @Positive Long id){
        userControllerContract.deleteUser(id);
    }
}
