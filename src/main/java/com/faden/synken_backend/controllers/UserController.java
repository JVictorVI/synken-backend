package com.faden.synken_backend.controllers;

import com.faden.synken_backend.dtos.UserRegisterDTO;
import com.faden.synken_backend.dtos.UserResponseDTO;
import com.faden.synken_backend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user/new")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {

        UserResponseDTO newUser = userService.createUser(userRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable(value = "id") UUID id, @RequestBody @Valid UserRegisterDTO userRegisterDTO) {

        UserResponseDTO updatedUser = userService.updateUser(id, userRegisterDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") UUID id) {

        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> getUserByID(@PathVariable(value = "id") UUID id) {

        UserResponseDTO foundUser = userService.getUserByID(id);
        return ResponseEntity.status(HttpStatus.OK).body(foundUser);
    }

    @GetMapping("/user/username/{username}")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable(value = "username") String username) {

        UserResponseDTO foundUserByUsername = userService.getUserByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(foundUserByUsername);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> foundUsers = userService.getAllUsers();
        return ResponseEntity.ok(foundUsers);
    }
}