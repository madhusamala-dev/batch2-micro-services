package com.trainingmug.ecommerce.userservice.dto.response;

import com.trainingmug.ecommerce.userservice.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserStatusRequestDto {
    @NotBlank(message = "Status is required")
    private int id;
    @NotBlank(message = "Status is required")
    private Status status;
    @NotBlank(message = "Reason is required")
    private String reason;
}
