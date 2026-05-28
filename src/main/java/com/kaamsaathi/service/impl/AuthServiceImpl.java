package com.kaamsaathi.service.impl;

import com.kaamsaathi.entity.User;
import com.kaamsaathi.repository.UserRepository;
import com.kaamsaathi.service.AuthService;
import com.kaamsaathi.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final OtpService otpService;
    private final UserRepository userRepository;

    @Override
    public void sendOtp(String phone) {
        otpService.sendOtp(phone);
    }

    @Override
    public User verifyOtpAndLogin(String phone, String otp) {

        boolean isValid = otpService.verifyOtp(phone, otp);

        if (!isValid) {
            throw new RuntimeException("Invalid OTP");
        }

        return userRepository.findByPhone(phone)
                .orElseGet(() -> {
                    User user = new User();
                    user.setPhone(phone);
                    user.setRole("CANDIDATE");
                    return userRepository.save(user);
                });
    }
}
