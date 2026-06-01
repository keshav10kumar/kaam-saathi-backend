package com.kaamsaathi.repository;

import com.kaamsaathi.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findByUserIdAndJobId(Long userId, Long jobId);

}
