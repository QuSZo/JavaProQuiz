package com.example.javapro.model.auth;

import com.example.javapro.enums.UserRole;

public record SignUpDto(
        String login,
        String password,
        UserRole role,
        String firstName,
        String lastName,
        String studentIdNumber) {
}
