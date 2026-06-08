package com.trainingmug.ecommerce.authservice.dto.request;

import lombok.Data;

@Data
public class LogoutRequestDto {
    private String refreshToken;
}
