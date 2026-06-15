package com.kaamsaathi.service;

public interface SessionService {

    String createSession(Long userId);

    Long getUserIdFromToken(String token);
}