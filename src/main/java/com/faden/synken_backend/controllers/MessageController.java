package com.faden.synken_backend.controllers;

import com.faden.synken_backend.dtos.MessageDTO;
import com.faden.synken_backend.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @Autowired
    MessageService messageService;

    @MessageMapping("/chat.sendPrivate")
    public void sendMessage(@Payload MessageDTO messageDTO) {
        messageService.sendPrivateMessage(messageDTO);
    }


}