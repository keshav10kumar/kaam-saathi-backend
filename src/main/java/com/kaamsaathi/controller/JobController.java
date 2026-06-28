package com.kaamsaathi.controller;

import com.kaamsaathi.dto.JobRequestDto;
import com.kaamsaathi.entity.Job;
import com.kaamsaathi.service.JobService;
import com.kaamsaathi.util.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/jobs")
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

    @PutMapping("/{jobId}")
    public Job updateJob(@PathVariable Long jobId,
                         @RequestBody JobRequestDto dto,
                         @RequestParam Long userId) {

        log.info("API: Update job request. jobId={}, userId={}", jobId, userId);

        return jobService.updateJob(jobId, dto, userId);
    }

    @DeleteMapping("/{jobId}")
    public String deleteJob(@PathVariable Long jobId,
                            @RequestParam Long userId) {

        log.info("API: Delete job request. jobId={}, userId={}", jobId, userId);

        jobService.deleteJob(jobId, userId);

        return Constants.Messages.JOB_DELETED_SUCCESSFULLY;
    }

    @GetMapping("/search")
    public List<Job> searchJobs(@RequestParam(required = false) String city,
                                @RequestParam(required = false) String keyword) {

        log.info("API: Search jobs. city={}, keyword={}",
                city != null ? city : "NULL",
                keyword != null ? keyword : "NULL");

        return jobService.searchJobs(city, keyword);
    }

}