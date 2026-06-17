package com.kaamsaathi.service;

import com.kaamsaathi.dto.ApplicationResponseDto;
import com.kaamsaathi.entity.Application;
import java.util.List;

public interface ApplicationService {

    String applyForJob(Long userId, Long jobId);
    List<ApplicationResponseDto> getApplicationsByJob(Long jobId);

}
