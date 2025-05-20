package com.faden.synken_backend.repositories;

import com.faden.synken_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsernameContaining(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}