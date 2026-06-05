package com.trainingmug.ecommerce.userservice.controller;

import com.trainingmug.ecommerce.userservice.dto.request.SignupRequestDto;
import com.trainingmug.ecommerce.userservice.dto.request.UserRequestDto;
import com.trainingmug.ecommerce.userservice.dto.response.ApiResponseDto;
import com.trainingmug.ecommerce.userservice.dto.response.UserResponseDto;
import com.trainingmug.ecommerce.userservice.dto.response.UserStatusRequestDto;
import com.trainingmug.ecommerce.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponseDto<UserResponseDto>> save(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponseDto.<UserResponseDto>builder().
                        success(true).
                        message("User Created Successfully").
                        status(HttpStatus.CREATED.value()).
                        data(userService.save(signupRequestDto)).
                        build());


    }

    @PutMapping
    public ResponseEntity<ApiResponseDto<UserResponseDto>> update(@Valid @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(
                ApiResponseDto.<UserResponseDto>builder().
                        success(true).
                        message("User Updated Successfully").
                        status(HttpStatus.OK.value()).
                        data(userService.update(userRequestDto)).
                        build());

    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<UserResponseDto>>> findAll() {
        return ResponseEntity.ok(
                ApiResponseDto.<List<UserResponseDto>>builder().
                        success(true).
                        status(HttpStatus.OK.value()).
                        message("Users Retrieved Successfully").
                        data(userService.findAll()).
                        build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> findById(@PathVariable int id) {
        return ResponseEntity.ok(
                ApiResponseDto.<UserResponseDto>builder().
                        success(true).
                        status(HttpStatus.OK.value()).
                        message("User Retrieved Successfully").
                        data(userService.findById(id)).
                        build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> delete(@PathVariable int id) {
        userService.delete(id);
        return ResponseEntity.ok(
                ApiResponseDto.<Void>builder().
                        success(true).
                        status(HttpStatus.OK.value()).
                        message("User Deleted Successfully").
                        build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponseDto<Boolean>> updateStatus(@PathVariable int id, @Valid @RequestBody UserStatusRequestDto userStatusRequestDto ) {
        return ResponseEntity.ok(
                ApiResponseDto.<Boolean>builder().
                        success(true).
                        status(HttpStatus.OK.value()).
                        message("User Status Updated Successfully").
                        data(userService.updateStatus(id, userStatusRequestDto)).
                        build());

    }
}
