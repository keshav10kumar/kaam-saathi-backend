package com.kaamsaathi.service.impl;

import com.kaamsaathi.service.OtpService;
import com.kaamsaathi.util.Constants;
import com.kaamsaathi.util.OtpStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final OtpStorage otpStorage;

    @Override
    public void sendOtp(String phone) {

        String otp = Constants.Common.OTP;

        otpStorage.saveOtp(phone, otp);

        // ✅ MASK PHONE
        String maskedPhone = phone.substring(phone.length() - 4);

        // ✅ SAFE LOG (NO OTP)
        log.info("OTP generated and stored for phone ending with {}", maskedPhone);
    }

    @Override
    public boolean verifyOtp(String phone, String otp) {

        String maskedPhone = phone.substring(phone.length() - 4);

        log.debug("Verifying OTP for phone ending with {}", maskedPhone);

        String storedOtp = otpStorage.getOtp(phone);

        if (storedOtp != null && storedOtp.equals(otp)) {

            otpStorage.removeOtp(phone);

            log.info("OTP verified successfully for phone ending with {}", maskedPhone);

            return true;
        }

        // ✅ FAILURE LOG (no OTP exposed)
        log.warn("Invalid OTP attempt for phone ending with {}", maskedPhone);

        return false;
    }
}