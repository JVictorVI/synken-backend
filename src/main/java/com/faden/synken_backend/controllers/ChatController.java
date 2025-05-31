package com.faden.synken_backend.controllers;

import com.faden.synken_backend.dtos.ChatResponseDTO;
import com.faden.synken_backend.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class ChatController {

    @Autowired
    ChatService chatService;

    // Todas as conversas disponíveis de um usuário
    @GetMapping("/chats/{id}")
    ResponseEntity<List<UUID>> getAllChatsFromUser(@PathVariable(value = "id") String username) {
        return ResponseEntity.ok(chatService.getChatsIDsFromUser(username));
    }

    // Conteúdo de uma conversa específica
    @GetMapping("/chat_content/{id}")
    ResponseEntity<ChatResponseDTO> getChatContent(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.ok(chatService.getChatContent(id));
    }

    // Conteúdo de uma conversa entre dois usuários
    @GetMapping("/chat/messages/{user1}/{user2}")
    ResponseEntity<ChatResponseDTO> getChatWithUser(@PathVariable(value = "user1") String user1,
                                                    @PathVariable(value = "user2") String user2) {
        return ResponseEntity.ok(chatService.getChatContentWithUsers(user1, user2));
    }
}
