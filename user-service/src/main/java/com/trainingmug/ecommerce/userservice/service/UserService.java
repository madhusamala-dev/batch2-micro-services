package com.trainingmug.ecommerce.userservice.service;

import com.trainingmug.ecommerce.userservice.dto.request.SignupRequestDto;
import com.trainingmug.ecommerce.userservice.dto.response.UserResponseDto;
import com.trainingmug.ecommerce.userservice.dto.request.UserRequestDto;
import com.trainingmug.ecommerce.userservice.dto.response.UserStatusRequestDto;
import com.trainingmug.ecommerce.userservice.entity.UserReponseDto;
import com.trainingmug.ecommerce.userservice.exception.UserExistsException;
import com.trainingmug.ecommerce.userservice.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    UserResponseDto save(SignupRequestDto userRequestDto) throws UserExistsException;
    UserReponseDto findByEmail(String email) throws UserNotFoundException;
    UserResponseDto update(UserRequestDto userRequestDto) throws UserNotFoundException;
    List<UserResponseDto> findAll();
    UserResponseDto findById(int id) throws UserNotFoundException;
    void delete(int id) throws UserNotFoundException;
    boolean updateStatus(int id, UserStatusRequestDto userStatusRequestDto) throws UserNotFoundException;
    boolean existsByEmail(String email);
}
