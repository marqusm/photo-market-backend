package com.marqus.photomarketbackend.service;

import com.marqus.photomarketbackend.dto.LoginResponseDto;
import com.marqus.photomarketbackend.dto.RegisterRequestDto;
import com.marqus.photomarketbackend.dto.LoginRequestDto;
import com.marqus.photomarketbackend.entity.Account;
import com.marqus.photomarketbackend.entity.Role;
import com.marqus.photomarketbackend.repository.AccountRepository;
import com.marqus.photomarketbackend.security.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtils jwtUtils;

    public void register(RegisterRequestDto req) {
        if (accountRepository.findByUsername(req.username()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        Account account = Account.builder()
                .username(req.username())
                .passwordHash(passwordEncoder.encode(req.password()))
                .role(Role.valueOf(req.role()))
                .name(req.name())
                .build();

        accountRepository.save(account);
    }

    public LoginResponseDto login(LoginRequestDto req) {
        Account acc = accountRepository.findByUsername(req.username())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(req.password(), acc.getPasswordHash())) {
            throw new BadCredentialsException("Invalid password");
        }

        String token = jwtUtils.generateToken(acc);
        return new LoginResponseDto(token);
    }
}