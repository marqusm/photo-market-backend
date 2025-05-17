package com.marqus.photomarketbackend.dto;


public record LoginRequest(
        String username,
        String password
) {
}
