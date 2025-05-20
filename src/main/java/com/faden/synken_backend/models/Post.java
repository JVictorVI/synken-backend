package com.faden.synken_backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Post implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPost;

    private String content;
    private String imgPost;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Vários posts terão um usuário
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
