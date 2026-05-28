package com.kaamsaathi.service;

import com.kaamsaathi.entity.User;

public interface AuthService {

    void sendOtp(String phone);

    User verifyOtpAndLogin(String phone, String otp);
}
