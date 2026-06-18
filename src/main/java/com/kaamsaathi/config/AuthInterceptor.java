package com.kaamsaathi.config;

import com.kaamsaathi.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // ✅ Allow CORS preflight
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String path = request.getRequestURI();

        // ✅ DEBUG (not info — this runs frequently)
        log.debug("Incoming request: {} {}", request.getMethod(), path);

        // ✅ Public APIs
        if (path.contains("/auth") || path.contains("/swagger") || path.contains("/actuator")) {
            return true;
        }

        String token = request.getHeader("Authorization");

        // ✅ Missing token
        if (token == null) {
            log.warn("Unauthorized request (no token): {} {}", request.getMethod(), path);

            response.setStatus(401);
            return false;
        }

        // ✅ Remove Bearer prefix
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // ✅ Validate token (NO token logging)
        Long userId = sessionService.getUserIdFromToken(token);

        if (userId == null) {
            log.warn("Unauthorized request (invalid token): {} {}", request.getMethod(), path);

            response.setStatus(401);
            return false;
        }

        // ✅ Set userId for downstream use
        request.setAttribute("userId", userId);

        // ✅ SUCCESS (keep DEBUG to avoid noise)
        log.debug("Authenticated request for userId={} {}", userId, path);

        return true;
    }
}