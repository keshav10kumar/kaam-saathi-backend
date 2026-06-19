package com.kaamsaathi.service.impl;

import com.kaamsaathi.dto.UserRequestDto;
import com.kaamsaathi.entity.User;
import com.kaamsaathi.repository.UserRepository;
import com.kaamsaathi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(UserRequestDto request) {

        // ✅ MASK PHONE
        String maskedPhone = request.getPhone()
                .substring(request.getPhone().length() - 4);

        log.info("Creating user for phone ending with {}", maskedPhone);

        User user = new User();
        user.setPhone(request.getPhone());
        user.setRole(request.getRole());
        user.setName(request.getName());
        user.setCity(request.getCity());
        user.setSkills(request.getSkills());

        User savedUser = userRepository.save(user);

        log.info("User created successfully. userId={}", savedUser.getId());

        return savedUser;
    }

    @Override
    public User getByPhone(String phone) {

        String maskedPhone = phone.substring(phone.length() - 4);

        log.debug("Fetching user by phone ending with {}", maskedPhone);

        return userRepository.findByPhone(phone)
                .orElseGet(() -> {
                    log.warn("User not found for phone ending with {}", maskedPhone);
                    return null;
                });
    }

    @Override
    public User updateProfile(UserRequestDto request) {

        String maskedPhone = request.getPhone()
                .substring(request.getPhone().length() - 4);

        log.info("Updating profile for phone ending with {}", maskedPhone);

        User user = userRepository.findByPhone(request.getPhone())
                .orElseThrow(() -> {
                    log.error("Profile update failed: User not found for phone ending with {}", maskedPhone);
                    return new RuntimeException("User not found");
                });

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

        if (request.getRole() != null) {
            user.setRole(request.getRole());
        }

        User updatedUser = userRepository.save(user);

        log.info("Profile updated successfully. userId={}", updatedUser.getId());

        return updatedUser;
    }
}