package com.faden.synken_backend.dtos;

import com.faden.synken_backend.models.Post;
import java.time.LocalDateTime;

public record PostResponseDTO

        (
            UserResponseDTO user,
            String content,
            String imgPost,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
        ) {

    public PostResponseDTO(Post post) {
        this (
                new UserResponseDTO(post.getUser()),
                post.getContent(),
                post.getImgPost(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}