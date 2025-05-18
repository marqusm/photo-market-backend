package com.marqus.photomarketbackend.util;

import com.marqus.photomarketbackend.controller.AuthController;
import com.marqus.photomarketbackend.dto.LoginRequestDto;
import com.marqus.photomarketbackend.dto.LoginResponseDto;
import com.marqus.photomarketbackend.dto.RegisterRequestDto;
import com.marqus.photomarketbackend.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.marqus.photomarketbackend.util.StringGenerator.generateRandomString;

@Component
public class TestHelper {

    @Autowired
    private AuthController authController;

    public void register(RegisterRequestDto request) {
        authController.register(request);
    }

    public LoginResponseDto registerAndLogin(Role role) {
        var registerRequest = new RegisterRequestDto(
                generateRandomString(10),
                generateRandomString(5),
                role.name(),
                generateRandomString(10)
        );
        authController.register(registerRequest);

        var loginRequest = new LoginRequestDto(registerRequest.username(), registerRequest.password());
        return authController.login(loginRequest).getBody();
    }
}
