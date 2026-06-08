package com.trainingmug.ecommerce.authservice.service;

import com.trainingmug.ecommerce.authservice.dto.request.*;
import com.trainingmug.ecommerce.authservice.dto.response.*;
import com.trainingmug.ecommerce.authservice.exception.*;

public interface AuthService {
    AuthResponseDto login(LoginRequestDto loginRequestDto) throws UserNotFoundException, InvalidCredentialsException;
    UserResponseDto signup(SignupRequestDto signupRequestDto) throws UserExistsException;
    ForgotPasswordResponseDto forgotPassword(
            ForgotPasswordRequestDto forgotPasswordRequestDto)
            throws UserNotFoundException;

    ResetPasswordResponseDto resetPassword(
            ResetPasswordRequestDto resetPasswordRequestDto)
            throws InvalidResetTokenException,
            UserNotFoundException;

    AuthResponseDto refreshToken(
            RefreshTokenRequestDto refreshTokenRequestDto)
            throws InvalidRefreshTokenException,
            RefreshTokenExpiredException;

    LogoutResponseDto logout(
            LogoutRequestDto logoutRequestDto)
            throws InvalidRefreshTokenException;
}
