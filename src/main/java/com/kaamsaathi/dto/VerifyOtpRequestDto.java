package com.kaamsaathi.dto;

import lombok.Data;

@Data
public class VerifyOtpRequestDto {
    private String phone;
    private String otp;
}
