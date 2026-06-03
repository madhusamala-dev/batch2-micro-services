package com.trainingmug.ecommerce.userservice.service;

import com.trainingmug.ecommerce.userservice.dto.response.AuthResponseDto;
import com.trainingmug.ecommerce.userservice.dto.request.LoginRequestDto;
import com.trainingmug.ecommerce.userservice.dto.request.SignupRequestDto;
import com.trainingmug.ecommerce.userservice.dto.response.UserResponseDto;
import com.trainingmug.ecommerce.userservice.exception.InvalidCredentialsException;
import com.trainingmug.ecommerce.userservice.exception.UserExistsException;
import com.trainingmug.ecommerce.userservice.exception.UserNotFoundException;

public interface AuthService {
    AuthResponseDto login(LoginRequestDto loginRequestDto) throws UserNotFoundException, InvalidCredentialsException;
    UserResponseDto signup(SignupRequestDto signupRequestDto) throws UserExistsException;
}
