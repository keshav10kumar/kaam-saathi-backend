package com.kaamsaathi.repository;

import com.kaamsaathi.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
