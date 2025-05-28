package com.faden.synken_backend.chat;

import com.faden.synken_backend.dtos.ChatResponseDTO;
import com.faden.synken_backend.dtos.PostResponseDTO;
import com.faden.synken_backend.models.Chat;
import com.faden.synken_backend.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ChatController {

    @Autowired
    ChatRepository chatRepository;

    @GetMapping("/chats/{id}")
    ResponseEntity<List<ChatResponseDTO>> getAllChatsFromUser(@PathVariable(value = "id") UUID id) {
        List<Chat> foundChats = chatRepository.findAllChatsFromUser(id);
        return ResponseEntity.ok(foundChats.stream().map(chat -> new ChatResponseDTO(chat)).toList());
    }

}
