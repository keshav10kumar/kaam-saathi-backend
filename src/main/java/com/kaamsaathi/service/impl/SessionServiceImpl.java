package com.kaamsaathi.service.impl;

import com.kaamsaathi.service.SessionService;
import com.kaamsaathi.util.TokenManager;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SessionServiceImpl implements SessionService {

    @Override
    public String createSession(Long userId) {

        // ✅ LOG — session creation
        log.info("Creating session for userId={}", userId);

        return TokenManager.generateToken(userId);
    }

    @Override
    public Long getUserIdFromToken(String token) {

        // ✅ LOG — token parsing (NO token logging)
        log.debug("Extracting userId from token");

        return TokenManager.getUserId(token);
    }
}