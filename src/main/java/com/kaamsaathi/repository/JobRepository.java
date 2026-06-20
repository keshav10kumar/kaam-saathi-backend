package com.kaamsaathi.repository;

import com.kaamsaathi.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    // ✅ Get all jobs (latest first)
    List<Job> findAllByOrderByCreatedAtDesc();

    // ✅ FIXED: Flexible city search (Delhi → New Delhi also works ✅)
    List<Job> findByCityContainingIgnoreCaseOrderByCreatedAtDesc(String city);

    // ✅ Recruiter jobs
    List<Job> findByCreatedByOrderByCreatedAtDesc(Long createdBy);

    // ✅ Keyword search (title + description)
    List<Job> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrderByCreatedAtDesc(
            String title, String description);

    // ✅ City + keyword combined search
    List<Job> findByCityContainingIgnoreCaseAndTitleContainingIgnoreCaseOrderByCreatedAtDesc(
            String city, String title);
}