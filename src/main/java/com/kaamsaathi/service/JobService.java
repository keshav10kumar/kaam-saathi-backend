package com.kaamsaathi.service;

import com.kaamsaathi.dto.JobRequestDto;
import com.kaamsaathi.entity.Job;

public interface JobService {
    Job createJob(JobRequestDto dto);
}
