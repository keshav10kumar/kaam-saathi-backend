package com.kaamsaathi.config;

import com.kaamsaathi.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {


// ✅ FIX: allow OPTIONS (CORS preflight)
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String path = request.getRequestURI();

        // Allow public APIs
        if (path.contains("/auth") || path.contains("/swagger")) {
            return true;
        }

        String token = request.getHeader("Authorization");

        if (token == null) {
            response.setStatus(401);
            return false;
        }

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        Long userId = sessionService.getUserIdFromToken(token);

        if (userId == null) {
            response.setStatus(401);
            return false;
        }

        request.setAttribute("userId", userId);

        return true;
    }
}
