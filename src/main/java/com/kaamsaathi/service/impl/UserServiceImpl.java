package com.kaamsaathi.service.impl;

import com.kaamsaathi.dto.UserRequestDto;
import com.kaamsaathi.entity.User;
import com.kaamsaathi.repository.UserRepository;
import com.kaamsaathi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(UserRequestDto request) {
        User user = new User();
        user.setPhone(request.getPhone());
        user.setRole(request.getRole());
        user.setName(request.getName());
        user.setCity(request.getCity());
        user.setSkills(request.getSkills());
        return userRepository.save(user);
    }

    @Override
    public User getByPhone(String phone) {
        return userRepository.findByPhone(phone).orElse(null);
    }

    @Override
    public User updateProfile(UserRequestDto request) {

        User user = userRepository.findByPhone(request.getPhone())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(request.getName());
        user.setCity(request.getCity());
        user.setSkills(request.getSkills());
        user.setRole(request.getRole()); // optional

        return userRepository.save(user);
    }

}
