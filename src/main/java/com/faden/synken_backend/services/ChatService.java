package com.faden.synken_backend.services;

import com.faden.synken_backend.dtos.ChatResponseDTO;
import com.faden.synken_backend.models.Chat;
import com.faden.synken_backend.models.User;
import com.faden.synken_backend.repositories.ChatRepository;
import com.faden.synken_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatService {

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    UserRepository userRepository;

    public ChatResponseDTO getChatContent(UUID id) {

        Chat chat = chatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chat não encontrado via ID: " + id));
        return new ChatResponseDTO(chat);
    }

    public ChatResponseDTO getChatContentWithUsers(String user1, String user2) {

        Optional<User> sessionUser = userRepository.findByUsername(user1);
        Optional<User> chatUser = userRepository.findByUsername(user2);

        Optional<Chat> foundChat = chatRepository.
                findChatWithUsers(sessionUser.get().getIdUser(), chatUser.get().getIdUser());

        return new ChatResponseDTO(foundChat.orElseThrow(() -> new RuntimeException("Chat não encontrado")));
    }

    public List<UUID> getChatsIDsFromUser(String username) {

        User foundUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        List<Chat> foundChats = chatRepository.findAllChatsFromUser(foundUser.getIdUser());

        return foundChats.stream().map(chat -> new ChatResponseDTO(chat).chat_id()).toList();
    }
}
