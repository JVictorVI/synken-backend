package com.faden.synken_backend.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ChatMessage {
    private String sender;
    private String recipient;
    private String content;
}
