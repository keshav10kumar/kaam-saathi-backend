package com.kaamsaathi.service.impl;

import com.kaamsaathi.dto.JobRequestDto;
import com.kaamsaathi.entity.Job;
import com.kaamsaathi.repository.JobRepository;
import com.kaamsaathi.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Job createJob(JobRequestDto dto, Long userId) {

        // ✅ ENTRY LOG (improved)
        log.info("Service: Creating job for userId={}, title={}, city={}",
                userId, dto.getTitle(), dto.getCity());

        Job job = new Job();
        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());
        job.setCity(dto.getCity());
        job.setSalary(dto.getSalary());
        job.setPhone(dto.getPhone());
        job.setCreatedBy(userId);

        Job savedJob = jobRepository.save(job);

        // ✅ SUCCESS LOG (improved)
        log.info("Job created successfully. jobId={}, createdBy={}",
                savedJob.getId(), savedJob.getCreatedBy());

        return savedJob;
    }

    @Override
    public List<Job> getAllJobs(String city) {

        // ✅ CLEAN LOG (avoid null confusion)
        log.debug("Service: Fetching jobs. city={}",
                (city != null && !city.isBlank()) ? city : "ALL");

        if (city != null && !city.isBlank()) {
            return jobRepository.findByCityIgnoreCaseOrderByCreatedAtDesc(city);
        }

        return jobRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public List<Job> getJobsByRecruiter(Long userId) {

        // ✅ ENTRY LOG
        log.debug("Service: Fetching jobs for recruiter userId={}", userId);

        List<Job> jobs = jobRepository.findByCreatedByOrderByCreatedAtDesc(userId);

        // ✅ RESULT LOG (very useful)
        log.info("Total jobs fetched for userId={} = {}", userId, jobs.size());

        return jobs;
    }
}