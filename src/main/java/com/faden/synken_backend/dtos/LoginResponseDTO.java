package com.faden.synken_backend.dtos;

public record LoginResponseDTO(UserResponseDTO user, String token) {}
