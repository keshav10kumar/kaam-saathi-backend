package com.kaamsaathi.controller;

import com.kaamsaathi.dto.UserRequestDto;
import com.kaamsaathi.entity.User;
import com.kaamsaathi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

//@CrossOrigin(origins = "http://localhost:5173")
@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User APIs")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create user")
    public User createUser(@Valid @RequestBody UserRequestDto request) {

        // ✅ LOG — user creation request (mask phone)
        String maskedPhone = request.getPhone()
                .substring(request.getPhone().length() - 4);

        log.info("Create user request for phone ending with: {}", maskedPhone);

        return userService.createUser(request);
    }

    @GetMapping("/{phone}")
    @Operation(summary = "Get user by phone")
    public User getUser(@PathVariable String phone) {

        // ✅ LOG — fetch user (masked)
        String maskedPhone = phone.substring(phone.length() - 4);

        log.info("Fetching user for phone ending with: {}", maskedPhone);

        return userService.getByPhone(phone);
    }

    @PutMapping("/profile")
    public User updateProfile(@Valid @RequestBody UserRequestDto request) {

        // ✅ LOG — profile update
        String maskedPhone = request.getPhone()
                .substring(request.getPhone().length() - 4);

        log.info("Updating profile for phone ending with: {}", maskedPhone);

        return userService.updateProfile(request);
    }

}