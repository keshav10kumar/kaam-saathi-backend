package com.kaamsaathi.controller;

import com.kaamsaathi.dto.OtpRequestDto;
import com.kaamsaathi.dto.VerifyOtpRequestDto;
import com.kaamsaathi.entity.User;
import com.kaamsaathi.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/send-otp")
    public String sendOtp(@Valid @RequestBody OtpRequestDto request) {
        authService.sendOtp(request.getPhone());
        return "OTP sent successfully";
    }

    @PostMapping("/verify-otp")
    public User verifyOtp(@Valid @RequestBody VerifyOtpRequestDto request) {
        return authService.verifyOtpAndLogin(
                request.getPhone(),
                request.getOtp()
        );
    }
}
