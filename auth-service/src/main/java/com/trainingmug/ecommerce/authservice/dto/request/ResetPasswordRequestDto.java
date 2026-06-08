package com.trainingmug.ecommerce.authservice.dto.request;

import lombok.Data;

@Data
public class ResetPasswordRequestDto {
    private String accessToken;
    private String newPassword;
    private String confirmPassword;
}
