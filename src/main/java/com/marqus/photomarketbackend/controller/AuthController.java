package com.marqus.photomarketbackend.controller;

import com.marqus.photomarketbackend.dto.LoginResponseDto;
import com.marqus.photomarketbackend.dto.RegisterRequestDto;
import com.marqus.photomarketbackend.dto.LoginRequestDto;
import com.marqus.photomarketbackend.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequestDto req) {
        authService.register(req);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto req) {
        return ResponseEntity.ok(authService.login(req));
    }
}