package com.faden.synken_backend.services;

import com.faden.synken_backend.dtos.UserRegisterDTO;
import com.faden.synken_backend.dtos.UserResponseDTO;
import com.faden.synken_backend.exceptions.user.UserNotFoundException;
import com.faden.synken_backend.models.User;
import com.faden.synken_backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private User getUser(UUID id) {
        return userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("Usuário não encontrado via ID: " + id));
    }

    @Transactional
    public UserResponseDTO createUser(UserRegisterDTO userRegisterDTO) {

        LocalDateTime currentDate = LocalDateTime.now();

        User newUser = new User();
        BeanUtils.copyProperties(userRegisterDTO, newUser);
        newUser.setCreatedAt(currentDate);

        User createdUser = userRepository.save(newUser);
        return new UserResponseDTO(createdUser);
    }

    @Transactional
    public UserResponseDTO updateUser(UUID id, UserRegisterDTO userRegisterDTO) {

        LocalDateTime currentDate = LocalDateTime.now();

        User userTobeUpdated = getUser(id);

        BeanUtils.copyProperties(userRegisterDTO, userTobeUpdated);
        userTobeUpdated.setUpdatedAt(currentDate);

        User updatedUser = userRepository.save(userTobeUpdated);

        return new UserResponseDTO(updatedUser);

    }

    @Transactional
    public void deleteUser(UUID id) {

        User foundUser = getUser(id);
        userRepository.delete(foundUser);
    }

    public UserResponseDTO getUserByID(UUID id) {

        User foundUser = getUser(id);
        return new UserResponseDTO(foundUser);
    }

    public UserResponseDTO getUserByUsername(String username) {

        User foundUser = userRepository.findByUsernameContaining(username).
                orElseThrow(() -> new UserNotFoundException("Usuário não encontrado via username: " + username));

        return new UserResponseDTO(foundUser);
    }

    public List<UserResponseDTO> getAllUsers() {

        List<User> foundUsers = userRepository.findAll();
        return foundUsers.stream().map(user -> new UserResponseDTO(user)).toList();
    }
}

