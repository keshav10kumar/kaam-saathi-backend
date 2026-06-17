package com.kaamsaathi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationResponseDto {

    private Long id;
    private String name;
    private String phone;
    private String city;
    private String skills;
    private Integer experience;
}
