package com.faden.synken_backend.repositories;

import com.faden.synken_backend.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {

    @Query("SELECT c FROM Chat c " +
            "WHERE (c.user1.id = :userId1 AND c.user2.id = :userId2) " +
            "OR (c.user1.id = :userId2 AND c.user2.id = :userId1)")
    Optional<Chat> findChatWithUsers(@Param("userId1") UUID user1ID, @Param("userId2") UUID user2ID);

    @Query("SELECT c FROM Chat c WHERE c.user1.id = :userId OR c.user2.id = :userId")
    List<Chat> findAllChatsFromUser(@Param("userId") UUID idUser);
}
