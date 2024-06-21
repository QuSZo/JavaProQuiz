package com.example.javaproserver.models.DTOs.auth;

import com.example.javaproserver.enums.UserRole;

public record SignUpDto(
        String login,
        String password,
        UserRole role,
        String firstName,
        String lastName,
        String studentIdNumber) {
}
