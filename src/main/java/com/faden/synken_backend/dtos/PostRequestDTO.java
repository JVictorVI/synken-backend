package com.faden.synken_backend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PostRequestDTO (
    @NotNull UUID idUser,
    @NotBlank String content,
    String imgPost
) {}