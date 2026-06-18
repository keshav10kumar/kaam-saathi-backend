package com.kaamsaathi.controller;

import com.kaamsaathi.dto.JobRequestDto;
import com.kaamsaathi.entity.Job;
import com.kaamsaathi.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/jobs")
//@CrossOrigin("*")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping
    public Job createJob(@Valid @RequestBody JobRequestDto dto, @RequestParam Long userId) {

        // ✅ LOG — incoming request
        log.info("Create job request: userId={}, title={}, city={}",
                userId, dto.getTitle(), dto.getCity());

        return jobService.createJob(dto, userId);
    }

    @GetMapping
    public List<Job> getJobs(@RequestParam(required = false) String city) {

        // ✅ IMPROVED LOG (avoid null confusion)
        log.info("Fetching jobs. city={}", (city != null && !city.isBlank()) ? city : "ALL");

        return jobService.getAllJobs(city);
    }

    @GetMapping("/recruiter/{userId}")
    public List<Job> getRecruiterJobs(@PathVariable Long userId) {

        // ✅ LOG — recruiter job list request
        log.info("Fetching jobs for recruiter userId={}", userId);

        return jobService.getJobsByRecruiter(userId);
    }

}