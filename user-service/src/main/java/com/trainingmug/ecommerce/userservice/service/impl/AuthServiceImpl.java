package com.trainingmug.ecommerce.userservice.service.impl;

import com.trainingmug.ecommerce.userservice.dto.response.AuthResponseDto;
import com.trainingmug.ecommerce.userservice.dto.request.LoginRequestDto;
import com.trainingmug.ecommerce.userservice.dto.request.SignupRequestDto;
import com.trainingmug.ecommerce.userservice.dto.response.UserResponseDto;
import com.trainingmug.ecommerce.userservice.entity.User;
import com.trainingmug.ecommerce.userservice.exception.InvalidCredentialsException;
import com.trainingmug.ecommerce.userservice.exception.UserExistsException;
import com.trainingmug.ecommerce.userservice.exception.UserNotFoundException;
import com.trainingmug.ecommerce.userservice.security.JwtUtil;
import com.trainingmug.ecommerce.userservice.service.AuthService;
import com.trainingmug.ecommerce.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class AuthServiceImpl
        implements AuthService {

    private final UserService userService;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    /*
        LOGIN
     */

    @Override
    public AuthResponseDto login(

            LoginRequestDto loginRequestDto

    ) throws UserNotFoundException,
            InvalidCredentialsException {

        /*
            Find User
         */

        User user =
                userService.findByEmail(
                        loginRequestDto.getEmail()
                );

        /*
            Validate Password
         */

        boolean isValidPassword =
                passwordEncoder.matches(

                        loginRequestDto.getPassword(),

                        user.getPassword()
                );

        if(!isValidPassword) {

            throw new InvalidCredentialsException(
                    "Invalid email or password"
            );
        }

        /*
            Generate Access Token
         */

        String accessToken =
                jwtUtil.generateAccessToken(
                        user
                );

        /*
            Generate Refresh Token
         */

        String refreshToken =
                jwtUtil.generateRefreshToken(
                        user
                );

        /*
            Return Response
         */

        return AuthResponseDto.builder()

                .accessToken(accessToken)

                .refreshToken(refreshToken)

                .user(

                        modelMapper.map(
                                user,
                                UserResponseDto.class
                        )
                )

                .build();
    }

    /*
        SIGNUP
     */

    @Override
    public UserResponseDto signup(

            SignupRequestDto signupRequestDto

    ) throws UserExistsException {

        /*
            Check Existing User
         */



           /* userService.findByEmail(
                    signupRequestDto.getEmail()
            );*/
        if(userService.existsByEmail(signupRequestDto.getEmail())) {
            throw new UserExistsException(
                    "User already exists with email : "
                            + signupRequestDto.getEmail()
            );
        }


        /*
            DTO -> ENTITY
         */





        /*
            Save User
         */

        return userService.save(signupRequestDto);
    }
}
