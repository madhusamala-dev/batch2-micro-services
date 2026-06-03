package com.trainingmug.ecommerce.userservice.controller;

import com.trainingmug.ecommerce.userservice.dto.response.AuthResponseDto;
import com.trainingmug.ecommerce.userservice.dto.request.LoginRequestDto;
import com.trainingmug.ecommerce.userservice.dto.request.SignupRequestDto;
import com.trainingmug.ecommerce.userservice.dto.response.UserResponseDto;
import com.trainingmug.ecommerce.userservice.exception.InvalidCredentialsException;
import com.trainingmug.ecommerce.userservice.exception.UserExistsException;
import com.trainingmug.ecommerce.userservice.exception.UserNotFoundException;
import com.trainingmug.ecommerce.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api/auth")

@RequiredArgsConstructor

public class AuthController {

    private final AuthService authService;

    /*
        LOGIN
     */

    @PostMapping("/login")

    public ResponseEntity<AuthResponseDto>
    login(

            @RequestBody
            LoginRequestDto loginRequestDto

    ) throws UserNotFoundException,
            InvalidCredentialsException {

        return ResponseEntity.ok(

                authService.login(
                        loginRequestDto
                )
        );
    }

    /*
        SIGNUP
     */

    @PostMapping("/signup")

    public ResponseEntity<UserResponseDto>
    signup(

            @RequestBody
            SignupRequestDto signupRequestDto

    ) throws UserExistsException {

        return ResponseEntity.status(HttpStatus.CREATED)

                .body(

                        authService.signup(
                                signupRequestDto
                        )
                );
    }
}
