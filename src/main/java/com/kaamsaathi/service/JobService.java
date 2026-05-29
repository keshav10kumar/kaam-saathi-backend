package com.kaamsaathi.service;

import com.kaamsaathi.dto.JobRequestDto;
import com.kaamsaathi.entity.Job;

import java.util.List;

public interface JobService {
    Job createJob(JobRequestDto dto);

    // ✅ NEW
    List<Job> getAllJobs(String city);

}
