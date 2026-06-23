package com.kaamsaathi.service.impl;

import com.kaamsaathi.dto.ApplicationResponseDto;
import com.kaamsaathi.entity.Application;
import com.kaamsaathi.repository.ApplicationRepository;
import com.kaamsaathi.repository.JobRepository;
import com.kaamsaathi.repository.UserRepository;
import com.kaamsaathi.service.ApplicationService;
import com.kaamsaathi.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    @Override
    public String applyForJob(Long userId, Long jobId) {

        // ✅ ENTRY LOG
        log.info("User {} attempting to apply for job {}", userId, jobId);

        // ✅ Check user exists
        userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("Apply failed: User not found. userId={}", userId);
                    return new RuntimeException("User not found");
                });

        // ✅ Check job exists
        jobRepository.findById(jobId)
                .orElseThrow(() -> {
                    log.error("Apply failed: Job not found. jobId={}", jobId);
                    return new RuntimeException("Job not found");
                });

        // ✅ Prevent duplicate apply
        applicationRepository.findByUserIdAndJobId(userId, jobId)
                .ifPresent(app -> {
                    log.warn("Duplicate apply attempt. userId={}, jobId={}", userId, jobId);
                    throw new RuntimeException("Already applied for this job");
                });

        // ✅ Save application
        Application application = new Application();
        application.setUserId(userId);
        application.setJobId(jobId);
        application.setStatus(Constants.Common.APPLIED);

        applicationRepository.save(application);

        // ✅ SUCCESS LOG
        log.info("User {} successfully applied to job {}", userId, jobId);

        return Constants.Messages.APPLIED_SUCCESSFULLY;
    }

    @Override
    public List<ApplicationResponseDto> getApplicationsByJob(Long jobId) {

        // ✅ ENTRY LOG
        log.debug("Fetching applications for jobId={}", jobId);

        List<Application> applications = applicationRepository.findByJobId(jobId);

        List<ApplicationResponseDto> response = new java.util.ArrayList<>();

        for (Application app : applications) {

            userRepository.findById(app.getUserId()).ifPresent(user -> {

                ApplicationResponseDto dto = new ApplicationResponseDto();

                dto.setId(app.getId());
                dto.setName(user.getName());
                dto.setPhone(user.getPhone());
                dto.setCity(user.getCity());
                dto.setSkills(user.getSkills());
                dto.setExperience(user.getExperience());

                response.add(dto);
            });
        }

        // ✅ RESULT LOG
        log.info("Total applications fetched for jobId={} = {}", jobId, response.size());

        return response;
    }
}