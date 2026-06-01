package com.kaamsaathi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OtpRequestDto {
    @NotBlank(message = "Phone is required")
    private String phone;
}
