package com.kaamsaathi.controller;

import com.kaamsaathi.dto.OtpRequestDto;
import com.kaamsaathi.dto.VerifyOtpRequestDto;
import com.kaamsaathi.entity.User;
import com.kaamsaathi.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.kaamsaathi.dto.AuthResponseDto;
import com.kaamsaathi.service.SessionService;


@RestController
@RequestMapping("/api/auth")
//@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final SessionService sessionService;

    @PostMapping("/send-otp")
    public String sendOtp(@Valid @RequestBody OtpRequestDto request) {
        authService.sendOtp(request.getPhone());
        return "OTP sent successfully";
    }

    @PostMapping("/verify-otp")
    public AuthResponseDto verifyOtp(@Valid @RequestBody VerifyOtpRequestDto request) {

        User user = authService.verifyOtpAndLogin(
                request.getPhone(),
                request.getOtp()
        );

        String token = sessionService.createSession(user.getId());

        AuthResponseDto response = new AuthResponseDto();
        response.setToken(token);
        response.setUserId(user.getId());
        response.setUser(user);

        return response;
    }

}
