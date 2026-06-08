package com.trainingmug.ecommerce.authservice.dto.response;

import com.trainingmug.ecommerce.authservice.enums.Role;
import com.trainingmug.ecommerce.authservice.enums.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    private int id;
    private String name;
    private String email;
    private Role role;
    private Status status;
}
