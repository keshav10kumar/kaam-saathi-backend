package com.kaamsaathi.repository;

import com.kaamsaathi.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findByUserIdAndJobId(Long userId, Long jobId);
    List<Application> findByJobId(Long jobId);

    // ✅ NEW: Applicant count for multiple jobs (single query)
    @Query("SELECT a.jobId, COUNT(a) FROM Application a WHERE a.jobId IN :jobIds GROUP BY a.jobId")
    List<Object[]> countApplicationsByJobIds(@Param("jobIds") List<Long> jobIds);



}
