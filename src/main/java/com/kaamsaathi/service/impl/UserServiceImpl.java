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

        if (request.getName() != null) {
            user.setName(request.getName());
        }
        if (request.getCity() != null) {
            user.setCity(request.getCity());
        }
        if (request.getSkills() != null) {
            user.setSkills(request.getSkills());
        }
        if (request.getAge() != null) {
            user.setAge(request.getAge());
        }
        if (request.getExperience() != null) {
            user.setExperience(request.getExperience());
        }
        return userRepository.save(user);
    }

}
