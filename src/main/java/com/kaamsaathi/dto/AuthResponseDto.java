package com.kaamsaathi.dto;

import lombok.Data;

@Data
public class AuthResponseDto {

    private String token;
    private Long userId;
    private Object user;
}
