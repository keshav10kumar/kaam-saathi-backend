package com.kaamsaathi.service.impl;

import com.kaamsaathi.dto.JobRequestDto;
import com.kaamsaathi.entity.Job;
import com.kaamsaathi.repository.JobRepository;
import com.kaamsaathi.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Job createJob(JobRequestDto dto) {

        Job job = new Job();
        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());
        job.setCity(dto.getCity());
        job.setSalary(dto.getSalary());
        job.setPhone(dto.getPhone());
        job.setCreatedBy(dto.getCreatedBy());

        return jobRepository.save(job);
    }
}