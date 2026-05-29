package com.kaamsaathi.controller;

import com.kaamsaathi.dto.JobRequestDto;
import com.kaamsaathi.entity.Job;
import com.kaamsaathi.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping
    public Job createJob(@RequestBody JobRequestDto dto) {
        return jobService.createJob(dto);
    }
}
