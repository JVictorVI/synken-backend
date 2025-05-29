package com.faden.synken_backend.dtos;

import java.time.LocalDateTime;

public record MessageDTO(String senderUsername, String receiverUsername, String content, LocalDateTime createdAt) {}
