package com.faden.synken_backend.dtos;

import com.faden.synken_backend.models.Chat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ChatResponseDTO(

        UUID chat_id,
        String user1,
        String user2,
        List<MessageDTO> messages,
        LocalDateTime createdAt

) {

    public ChatResponseDTO(Chat chat) {
        this (
                chat.getIdChat(),
                chat.getUser1().getUsername(),
                chat.getUser2().getUsername(),
                chat.getMessages().stream().map(
                        chat_content -> new MessageDTO(
                                chat_content.getSender().getUsername(),
                                chat_content.getRecipient().getUsername(),
                                chat_content.getContent(),
                                chat_content.getCreatedAt()
                                )).toList(),
                chat.getCreatedAt()

        );
    }
}
