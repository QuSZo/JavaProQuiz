package com.example.javapro.model.auth;

import com.example.javapro.enums.UserRole;

import java.util.UUID;

public record JwtDto(
        String accessToken,
        String userId,
        UserRole userRole) {
}