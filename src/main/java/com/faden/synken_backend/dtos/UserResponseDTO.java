package com.faden.synken_backend.dtos;

import com.faden.synken_backend.models.User;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDTO

        (
                UUID id,
                String name,
                String username,
                String profilePicture,
                LocalDateTime createdAt,
                LocalDateTime updatedAt
        ) {

    public UserResponseDTO(User user) {

        this (
                user.getIdUser(),
                user.getName(),
                user.getUsername(),
                user.getProfilePicture(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}