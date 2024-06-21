package com.example.javaproserver.models.DTOs.auth;

import com.example.javaproserver.enums.UserRole;

import java.util.UUID;

public record JwtDto(
        String accessToken,
        UUID userId,
        UserRole userRole) {
}