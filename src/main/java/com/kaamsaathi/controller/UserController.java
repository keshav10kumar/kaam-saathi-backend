package com.kaamsaathi.controller;

import com.kaamsaathi.dto.UserRequestDto;
import com.kaamsaathi.entity.User;
import com.kaamsaathi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User APIs")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create user")
    public User createUser(@Valid @RequestBody UserRequestDto request) {
        return userService.createUser(request);
    }

    @GetMapping("/{phone}")
    @Operation(summary = "Get user by phone")
    public User getUser(@PathVariable String phone) {
        return userService.getByPhone(phone);
    }

    @PutMapping("/profile")
    public User updateProfile(@Valid @RequestBody UserRequestDto request) {
        return userService.updateProfile(request);
    }

}
