package com.kaamsaathi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDto {

    @NotBlank(message = "Phone is required")
    private String phone;

    private String role;
    private String name;
    private String city;
    private String skills;
    private Integer age;
    private Integer experience;
}
