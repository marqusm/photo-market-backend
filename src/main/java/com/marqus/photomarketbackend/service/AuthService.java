package com.marqus.photomarketbackend.service;

import com.marqus.photomarketbackend.dto.LoginResponse;
import com.marqus.photomarketbackend.dto.RegisterRequest;
import com.marqus.photomarketbackend.dto.LoginRequest;
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

    public void register(RegisterRequest req) {
        if (accountRepository.findByUsername(req.username()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        Account account = new Account();
        account.setUsername(req.username());
        account.setPasswordHash(passwordEncoder.encode(req.password()));
        account.setRole(Role.valueOf(req.role()));
        account.setName(req.name());

        accountRepository.save(account);
    }

    public LoginResponse login(LoginRequest req) {
        Account acc = accountRepository.findByUsername(req.username())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(req.password(), acc.getPasswordHash())) {
            throw new BadCredentialsException("Invalid password");
        }

        String token = jwtUtils.generateToken(acc);
        return new LoginResponse(token);
    }
}