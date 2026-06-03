package com.trainingmug.ecommerce.userservice.dto.response;

import com.trainingmug.ecommerce.userservice.enums.Role;
import com.trainingmug.ecommerce.userservice.enums.Status;
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
