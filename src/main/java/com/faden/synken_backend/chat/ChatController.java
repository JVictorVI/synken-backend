package com.faden.synken_backend.chat;

import com.faden.synken_backend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/chat.sendPrivatePublic") // endpoint para envio de msg privada pública
    public void sendPrivatePublicMessage(@Payload ChatMessage message) {
        System.out.println("Enviando para tópico: /topic/private." + message.getRecipient());
        System.out.println("Mensagem: " + message.getContent());

        // Envia mensagem para tópico público "privado" do destinatário
        messagingTemplate.convertAndSend(
                "/topic/private." + message.getRecipient(),
                message
        );
    }


}