package com.kaamsaathi.service.impl;

import com.kaamsaathi.entity.User;
import com.kaamsaathi.repository.UserRepository;
import com.kaamsaathi.service.AuthService;
import com.kaamsaathi.service.OtpService;
import com.kaamsaathi.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final OtpService otpService;
    private final UserRepository userRepository;

    @Override
    public void sendOtp(String phone) {

        // ✅ MASK PHONE
        String maskedPhone = phone.substring(phone.length() - 4);

        log.info("Service: Sending OTP to phone ending with {}", maskedPhone);

        otpService.sendOtp(phone);
    }

    @Override
    public User verifyOtpAndLogin(String phone, String otp) {

        // ✅ MASK PHONE
        String maskedPhone = phone.substring(phone.length() - 4);

        log.info("Service: Verifying OTP for phone ending with {}", maskedPhone);

        boolean isValid = otpService.verifyOtp(phone, otp);

        if (!isValid) {
            log.warn("OTP verification failed for phone ending with {}", maskedPhone);
            throw new RuntimeException(Constants.Messages.INVALID_OTP);
        }

        log.info("OTP verified successfully for phone ending with {}", maskedPhone);

        return userRepository.findByPhone(phone)
                .map(user -> {

                    // ✅ EXISTING USER
                    log.debug("User found in DB. userId={}", user.getId());

                    if (user.getRole() == null) {
                        user.setRole(Constants.Roles.CANDIDATE);
                        userRepository.save(user);

                        log.info("Default role assigned to userId={}", user.getId());
                    }

                    return user;
                })
                .orElseGet(() -> {

                    // ✅ NEW USER CREATION
                    log.info("Creating new user for phone ending with {}", maskedPhone);

                    User user = new User();
                    user.setPhone(phone);
                    user.setRole(Constants.Roles.CANDIDATE);

                    User savedUser = userRepository.save(user);

                    log.info("New user created. userId={}", savedUser.getId());

                    return savedUser;
                });
    }
}