package com.kaamsaathi.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@Data
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private Long applicantCount = 0L;

    private String title;
    private String description;
    private String city;
    private String salary;
    private String phone;
    private Long createdBy;

    @CreationTimestamp
    private LocalDateTime createdAt;
}