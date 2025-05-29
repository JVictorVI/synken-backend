package com.faden.synken_backend.chat;

import com.faden.synken_backend.dtos.ChatResponseDTO;
import com.faden.synken_backend.dtos.PostResponseDTO;
import com.faden.synken_backend.models.Chat;
import com.faden.synken_backend.models.User;
import com.faden.synken_backend.repositories.ChatRepository;
import com.faden.synken_backend.repositories.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    // Todas as conversas disponíveis de um usuário
    @GetMapping("/chats/{id}")
    ResponseEntity<List<ChatResponseDTO>> getAllChatsFromUser(@PathVariable(value = "id") UUID id) {
        List<Chat> foundChats = chatRepository.findAllChatsFromUser(id);
        return ResponseEntity.ok(foundChats.stream().map(chat -> new ChatResponseDTO(chat)).toList());
    }

    // Conteúdo de uma conversa específica
    @GetMapping("/chat_content/{id}")
    ResponseEntity<ChatResponseDTO> getChatContent(@PathVariable(value = "id") UUID id) {
        Optional<Chat> foundChat = chatRepository.findById(id);
        return ResponseEntity.ok(new ChatResponseDTO(foundChat.get()));
    }

    @GetMapping("/chat/messages/{user1}/{user2}")
    ResponseEntity<ChatResponseDTO> getChatWithUser(@PathVariable(value = "user1") String user1,
                                                    @PathVariable(value = "user2") String user2) {
        Optional<User> sessionUser = userRepository.findByUsername(user1);
        Optional<User> chatUser = userRepository.findByUsername(user2);
        Optional<Chat> foundChat = chatRepository.findChatWithUsers(sessionUser.get().getIdUser(), chatUser.get().getIdUser());

        return ResponseEntity.ok(new ChatResponseDTO(foundChat.get()));
    }
}
