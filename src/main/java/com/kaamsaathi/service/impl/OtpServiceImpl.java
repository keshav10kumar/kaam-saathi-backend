package com.kaamsaathi.service.impl;

import com.kaamsaathi.service.OtpService;
import com.kaamsaathi.util.OtpStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final OtpStorage otpStorage;

    @Override
    public void sendOtp(String phone) {
        String otp = "1234"; // temporary
        otpStorage.saveOtp(phone, otp);

        System.out.println("OTP for " + phone + " is " + otp);
    }

    @Override
    public boolean verifyOtp(String phone, String otp) {

        String storedOtp = otpStorage.getOtp(phone);

        if (storedOtp != null && storedOtp.equals(otp)) {
            otpStorage.removeOtp(phone);
            return true;
        }

        return false;
    }
}

