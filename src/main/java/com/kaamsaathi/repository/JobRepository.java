package com.kaamsaathi.repository;

import com.kaamsaathi.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findAllByOrderByCreatedAtDesc();
    List<Job> findByCityIgnoreCaseOrderByCreatedAtDesc(String city);
    List<Job> findByCreatedByOrderByCreatedAtDesc(Long createdBy);

}
