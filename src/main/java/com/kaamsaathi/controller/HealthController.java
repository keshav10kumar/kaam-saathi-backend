package com.kaamsaathi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class HealthController {

    @GetMapping("/health")
    public String health() {

        // ✅ VERY LIGHT LOG (optional but useful)
        log.debug("Health check endpoint called");

        return "KaamSaathi Backend Running ✅";
    }
}