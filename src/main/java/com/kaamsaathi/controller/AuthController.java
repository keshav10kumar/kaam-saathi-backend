package com.kaamsaathi.controller;

import com.kaamsaathi.dto.OtpRequestDto;
import com.kaamsaathi.dto.VerifyOtpRequestDto;
import com.kaamsaathi.entity.User;
import com.kaamsaathi.service.AuthService;
import com.kaamsaathi.util.Constants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.kaamsaathi.dto.AuthResponseDto;
import com.kaamsaathi.service.SessionService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final SessionService sessionService;

    @PostMapping("/send-otp")
    public String sendOtp(@Valid @RequestBody OtpRequestDto request) {

        // ✅ MASK PHONE (only last 4 digits)
        String maskedPhone = request.getPhone()
                .substring(request.getPhone().length() - 4);

        log.info("Sending OTP to phone ending with: {}", maskedPhone);

        authService.sendOtp(request.getPhone());

        //return "OTP sent successfully";
        return Constants.Messages.OTP_SENT;
    }

    @PostMapping("/verify-otp")
    public AuthResponseDto verifyOtp(@Valid @RequestBody VerifyOtpRequestDto request) {

        // ✅ MASK PHONE
        String maskedPhone = request.getPhone()
                .substring(request.getPhone().length() - 4);

        log.info("Verifying OTP for phone ending with: {}", maskedPhone);

        User user = authService.verifyOtpAndLogin(
                request.getPhone(),
                request.getOtp()
        );

        log.info("User logged in successfully. userId={}", user.getId());

        String token = sessionService.createSession(user.getId());

        AuthResponseDto response = new AuthResponseDto();
        response.setToken(token);
        response.setUserId(user.getId());
        response.setUser(user);

        return response;
    }
}