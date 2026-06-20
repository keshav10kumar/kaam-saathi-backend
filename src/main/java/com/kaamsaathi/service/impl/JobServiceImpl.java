package com.kaamsaathi.service.impl;

import com.kaamsaathi.dto.JobRequestDto;
import com.kaamsaathi.entity.Job;
import com.kaamsaathi.repository.ApplicationRepository;
import com.kaamsaathi.repository.JobRepository;
import com.kaamsaathi.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

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
            return jobRepository.findByCityContainingIgnoreCaseOrderByCreatedAtDesc(city);
        }

        return jobRepository.findAllByOrderByCreatedAtDesc();
    }

//    @Override
//    public List<Job> getJobsByRecruiter(Long userId) {
//
//        // ✅ ENTRY LOG
//        log.debug("Service: Fetching jobs for recruiter userId={}", userId);
//
//        List<Job> jobs = jobRepository.findByCreatedByOrderByCreatedAtDesc(userId);
//
//        // ✅ RESULT LOG (very useful)
//        log.info("Total jobs fetched for userId={} = {}", userId, jobs.size());
//
//        return jobs;
//    }

    @Override
    public List<Job> getJobsByRecruiter(Long userId) {

        log.debug("Service: Fetching jobs for recruiter userId={}", userId);

        List<Job> jobs = jobRepository.findByCreatedByOrderByCreatedAtDesc(userId);

        // ✅ If no jobs, return early (safe guard)
        if (jobs.isEmpty()) {
            return jobs;
        }

        // ✅ Step 1: Extract jobIds
        List<Long> jobIds = jobs.stream()
                .map(Job::getId)
                .toList();

        // ✅ Step 2: Fetch applicant counts in ONE query
        List<Object[]> results = applicationRepository.countApplicationsByJobIds(jobIds);

        // ✅ Step 3: Convert result → Map<jobId, count>
        Map<Long, Long> countMap = new HashMap<>();

        for (Object[] row : results) {
            Long jobId = (Long) row[0];
            Long count = (Long) row[1];
            countMap.put(jobId, count);
        }

        // ✅ Step 4: Attach count to each job
        jobs.forEach(job -> {
            Long count = countMap.getOrDefault(job.getId(), 0L);
            job.setApplicantCount(count);
        });

        log.info("Total jobs fetched for userId={} = {}", userId, jobs.size());

        return jobs;
    }


    @Override
    public Job updateJob(Long jobId, JobRequestDto dto, Long userId) {

        log.info("Updating jobId={} by userId={}", jobId, userId);

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // ✅ Ownership check
        validateOwner(job, userId);

        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());
        job.setCity(dto.getCity());
        job.setSalary(dto.getSalary());
        job.setPhone(dto.getPhone());

        Job updatedJob = jobRepository.save(job);

        log.info("Job updated successfully. jobId={}", updatedJob.getId());

        return updatedJob;
    }

    @Override
    public void deleteJob(Long jobId, Long userId) {

        log.info("Deleting jobId={} by userId={}", jobId, userId);

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // ✅ Ownership check
        validateOwner(job, userId);

        jobRepository.delete(job);

        log.info("Job deleted successfully. jobId={}", jobId);
    }

    @Override
    public List<Job> searchJobs(String city, String keyword) {

        log.info("Searching jobs. city={}, keyword={}",
                (city != null ? city : "NULL"),
                (keyword != null ? keyword : "NULL"));

        // ✅ BOTH city + keyword
        if (city != null && !city.isBlank() &&
                keyword != null && !keyword.isBlank()) {

            return jobRepository
                    .findByCityContainingIgnoreCaseAndTitleContainingIgnoreCaseOrderByCreatedAtDesc(city, keyword);
        }

        // ✅ ONLY city
        if (city != null && !city.isBlank()) {
            return jobRepository.findByCityContainingIgnoreCaseOrderByCreatedAtDesc(city);
        }

        // ✅ ONLY keyword
        if (keyword != null && !keyword.isBlank()) {
            return jobRepository
                    .findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrderByCreatedAtDesc(keyword, keyword);
        }

        // ✅ NO FILTER → return all
        return jobRepository.findAllByOrderByCreatedAtDesc();
    }

    private void validateOwner(Job job, Long userId) {

        if (!job.getCreatedBy().equals(userId)) {
            log.error("Unauthorized access. jobId={}, ownerId={}, requestUserId={}",
                    job.getId(), job.getCreatedBy(), userId);

            throw new RuntimeException("You are not authorized to perform this action");
        }
    }

}