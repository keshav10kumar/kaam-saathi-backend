package com.kaamsaathi.util;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OtpStorage {

    private final Map<String, String> otpMap = new ConcurrentHashMap<>();

    public void saveOtp(String phone, String otp) {
        otpMap.put(phone, otp);
    }

    public String getOtp(String phone) {
        return otpMap.get(phone);
    }

    public void removeOtp(String phone) {
        otpMap.remove(phone);
    }
}