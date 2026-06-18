package com.kaamsaathi.controller;

import com.kaamsaathi.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/{jobId}/apply")
    public String applyJob(@PathVariable Long jobId,
                           @RequestParam Long userId) {

        // ✅ LOG — incoming request
        log.info("Apply job request: userId={}, jobId={}", userId, jobId);

        return applicationService.applyForJob(userId, jobId);
    }

    @GetMapping("/{jobId}/applications")
    public Object getApplicants(@PathVariable Long jobId) {

        // ✅ LOG — fetching applicants
        log.info("Fetching applicants for jobId={}", jobId);

        return applicationService.getApplicationsByJob(jobId);
    }
}