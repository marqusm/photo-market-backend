package com.marqus.photomarketbackend.dto;

public record RegisterRequest(
        String username,
        String password,
        String role,
        String name
) {
}
