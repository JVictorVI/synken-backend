package com.faden.synken_backend.repositories;

import com.faden.synken_backend.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {}
