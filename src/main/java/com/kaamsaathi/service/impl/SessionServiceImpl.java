package com.kaamsaathi.service.impl;

import com.kaamsaathi.service.SessionService;
import com.kaamsaathi.util.TokenManager;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {

    @Override
    public String createSession(Long userId) {
        return TokenManager.generateToken(userId);
    }

    @Override
    public Long getUserIdFromToken(String token) {
        return TokenManager.getUserId(token);
    }
}
