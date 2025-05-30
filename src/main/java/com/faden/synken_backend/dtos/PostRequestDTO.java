package com.faden.synken_backend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PostRequestDTO (
    String username,
    String content,
    String imgPost
) {}