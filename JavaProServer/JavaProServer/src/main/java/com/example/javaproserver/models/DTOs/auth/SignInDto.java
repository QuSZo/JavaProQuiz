package com.example.javaproserver.models.DTOs.auth;

public record SignInDto(
        String login,
        String password) {
}