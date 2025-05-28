package com.faden.synken_backend.chat;

import com.faden.synken_backend.dtos.MessageDTO;
import com.faden.synken_backend.models.Message;
import com.faden.synken_backend.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @Autowired
    MessageService messageService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message message) {
        return message;
    }

    @MessageMapping("/chat.sendPrivate")
    public void sendPrivatePublicMessage(@Payload MessageDTO messageDTO) {
        messageService.sendPrivateMessage(messageDTO);
    }


}