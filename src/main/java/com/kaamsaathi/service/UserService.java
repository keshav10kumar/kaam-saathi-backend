package com.kaamsaathi.service;

import com.kaamsaathi.dto.UserRequestDto;
import com.kaamsaathi.entity.User;

public interface UserService {
    User createUser(UserRequestDto request);
    User getByPhone(String phone);
    User updateProfile(UserRequestDto request);
}
