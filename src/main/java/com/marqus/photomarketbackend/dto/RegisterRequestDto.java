package com.marqus.photomarketbackend.dto;

public record RegisterRequestDto(
        String username,
        String password,
        String role,
        String name) {
}
