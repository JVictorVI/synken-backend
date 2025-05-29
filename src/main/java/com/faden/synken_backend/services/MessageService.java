package com.faden.synken_backend.services;

import com.faden.synken_backend.models.Chat;
import com.faden.synken_backend.models.Message;
import com.faden.synken_backend.dtos.MessageDTO;
import com.faden.synken_backend.models.User;
import com.faden.synken_backend.repositories.ChatRepository;
import com.faden.synken_backend.repositories.MessageRepository;
import com.faden.synken_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    UserRepository userRepository;

    public void sendPrivateMessage(MessageDTO message) {

        messagingTemplate.convertAndSend(
                "/topic/private." + message.receiverUsername(),
                message
        );

        LocalDateTime currentDate = LocalDateTime.now();

        User sender = userRepository.findByUsername(message.senderUsername()).
                orElseThrow(() -> new UsernameNotFoundException("Username não encontrado"));

        User receiver = userRepository.findByUsername(message.receiverUsername()).
                orElseThrow(() -> new UsernameNotFoundException("Username não encontrado"));

        Chat chat = chatRepository.findChatWithUsers(sender.getIdUser(), receiver.getIdUser())
                .orElseGet(() -> {
                    Chat newChat = new Chat();
                    newChat.setUser1(sender);
                    newChat.setUser2(receiver);
                    newChat.setCreatedAt(currentDate);
                    return chatRepository.save(newChat);
                });

        Message newMessage = new Message();
        newMessage.setContent(message.content());
        newMessage.setCreatedAt(currentDate);
        newMessage.setSender(sender);
        newMessage.setRecipient(receiver);
        newMessage.setChat(chat);
        messageRepository.save(newMessage);
    }
}
