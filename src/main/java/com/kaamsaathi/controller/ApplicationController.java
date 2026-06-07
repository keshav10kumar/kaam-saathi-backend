package com.kaamsaathi.controller;

import com.kaamsaathi.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/{jobId}/apply")
    public String applyJob(@PathVariable Long jobId,
                           @RequestParam Long userId) {

        return applicationService.applyForJob(userId, jobId);
    }
}
