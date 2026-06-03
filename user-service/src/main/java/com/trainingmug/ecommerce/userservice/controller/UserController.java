package com.trainingmug.ecommerce.userservice.controller;

import com.trainingmug.ecommerce.userservice.dto.request.SignupRequestDto;
import com.trainingmug.ecommerce.userservice.dto.request.UserRequestDto;
import com.trainingmug.ecommerce.userservice.dto.response.UserResponseDto;
import com.trainingmug.ecommerce.userservice.dto.response.UserStatusRequestDto;
import com.trainingmug.ecommerce.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> save(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        return ResponseEntity.ok(userService.save(signupRequestDto));
    }

    @PutMapping
    public ResponseEntity<UserResponseDto> update(@Valid @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.update(userRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable int id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Boolean> updateStatus(@PathVariable int id, @Valid @RequestBody UserStatusRequestDto userStatusRequestDto ) {
        return ResponseEntity.ok(userService.updateStatus(id, userStatusRequestDto));
    }
}
