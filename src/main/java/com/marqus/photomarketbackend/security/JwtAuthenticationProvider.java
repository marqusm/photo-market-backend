package com.marqus.photomarketbackend.security;

import com.marqus.photomarketbackend.entity.Account;
import com.marqus.photomarketbackend.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String rawPassword = authentication.getCredentials().toString();

        Account account = accountRepository.findByUsername(username).orElseThrow();

        if (!passwordEncoder.matches(rawPassword, account.getPasswordHash())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(
                account,
                null,
                List.of(new SimpleGrantedAuthority(account.getRole().toString()))
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
