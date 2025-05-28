package com.faden.synken_backend.dtos;

import com.faden.synken_backend.models.Chat;
import com.faden.synken_backend.models.Message;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ChatResponseDTO(

        UUID chat_id,
        UserResponseDTO user1,
        UserResponseDTO user2,
        List<Message> messages,
        LocalDateTime createdAt

) {

    public ChatResponseDTO(Chat chat) {
        this (
                chat.getIdChat(),
                new UserResponseDTO(chat.getUser1()),
                new UserResponseDTO(chat.getUser2()),
                chat.getMessages(),
                chat.getCreatedAt()

        );
    }
}
