package com.kaamsaathi.dto;

import lombok.Data;

@Data
public class JobRequestDto {

    private String title;
    private String description;
    private String city;
    private String salary;
    private String phone;
    private Long createdBy;
}