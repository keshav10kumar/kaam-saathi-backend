package com.kaamsaathi.dto;

import com.kaamsaathi.entity.User;
import lombok.Data;

@Data
public class AuthResponseDto {

    private String token;
    private Long userId;
    private User user;   // ✅ Type-safe now
}
