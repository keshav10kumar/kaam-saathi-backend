package com.kaamsaathi.controller;

import com.kaamsaathi.dto.JobRequestDto;
import com.kaamsaathi.entity.Job;
import com.kaamsaathi.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jobs")
//@CrossOrigin("*")
public class JobController {

    @Autowired
    private JobService jobService;

    // ✅ EXISTING (unchanged)
    @PostMapping
    public Job createJob(@Valid @RequestBody JobRequestDto dto) {
        return jobService.createJob(dto);
    }

    // ✅ NEW (Day 5)
    @GetMapping
    public List<Job> getJobs(@RequestParam(required = false) String city) {
        return jobService.getAllJobs(city);
    }
}