package com.kaamsaathi.service.impl;

import com.kaamsaathi.entity.Application;
import com.kaamsaathi.repository.ApplicationRepository;
import com.kaamsaathi.repository.JobRepository;
import com.kaamsaathi.repository.UserRepository;
import com.kaamsaathi.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    @Override
    public String applyForJob(Long userId, Long jobId) {

        // ✅ Check user exists
        userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ Check job exists
        jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // ✅ Prevent duplicate apply
        applicationRepository.findByUserIdAndJobId(userId, jobId)
                .ifPresent(app -> {
                    throw new RuntimeException("Already applied for this job");
                });

        // ✅ Save application
        Application application = new Application();
        application.setUserId(userId);
        application.setJobId(jobId);
        application.setStatus("APPLIED");

        applicationRepository.save(application);

        return "Applied successfully";
    }
}