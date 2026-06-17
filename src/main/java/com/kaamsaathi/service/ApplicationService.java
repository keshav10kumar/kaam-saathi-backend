package com.kaamsaathi.service;

import com.kaamsaathi.entity.Application;
import java.util.List;

public interface ApplicationService {

    String applyForJob(Long userId, Long jobId);
    List<Application> getApplicationsByJob(Long jobId);

}
