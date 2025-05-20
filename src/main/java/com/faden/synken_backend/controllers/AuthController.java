package com.faden.synken_backend.controllers;

import com.faden.synken_backend.dtos.LoginDTO;
import com.faden.synken_backend.dtos.LoginResponseDTO;
import com.faden.synken_backend.dtos.UserRegisterDTO;
import com.faden.synken_backend.dtos.UserResponseDTO;
import com.faden.synken_backend.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        LoginResponseDTO loginResponse = authService.doLogin(loginDTO);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRegisterDTO userRegisterDTO) {

        UserResponseDTO registerResponse = authService.doRegister(userRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);

    }
}
