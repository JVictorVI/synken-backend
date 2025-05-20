package com.faden.synken_backend.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserRegisterDTO(
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank String username,
        String profilePicture) {}
