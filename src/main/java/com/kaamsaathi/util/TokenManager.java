package com.kaamsaathi.util;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TokenManager {

    private static final Map<String, Long> tokenStore = new ConcurrentHashMap<>();

    public static String generateToken(Long userId) {
        String token = UUID.randomUUID().toString();
        tokenStore.put(token, userId);
        return token;
    }

    public static Long getUserId(String token) {
        return tokenStore.get(token);
    }
}
