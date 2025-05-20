package com.faden.synken_backend.services;

import com.faden.synken_backend.dtos.LoginDTO;
import com.faden.synken_backend.dtos.LoginResponseDTO;
import com.faden.synken_backend.dtos.UserRegisterDTO;
import com.faden.synken_backend.dtos.UserResponseDTO;
import com.faden.synken_backend.exceptions.user.EmailAlreadyExistsException;
import com.faden.synken_backend.exceptions.user.UserNotFoundException;
import com.faden.synken_backend.exceptions.user.UserWrongPasswordException;
import com.faden.synken_backend.exceptions.user.UsernameAlreadyExistsException;
import com.faden.synken_backend.infra.security.TokenService;
import com.faden.synken_backend.models.User;
import com.faden.synken_backend.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    public LoginResponseDTO doLogin(LoginDTO loginDTO) {

        User loginUser = userRepository.findByEmail(loginDTO.email()).
                orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));

        if (passwordEncoder.matches(loginDTO.password(), loginUser.getPassword())) {
            String token = tokenService.generateToken(loginUser);
            UserResponseDTO loginUserDTO = new UserResponseDTO(loginUser);
            return new LoginResponseDTO(loginUserDTO, token);

        } else {
            throw new UserWrongPasswordException("Senha incorreta.");
        }
    }

    public UserResponseDTO doRegister(UserRegisterDTO userRegisterDTO) {

        userRepository.findByEmail(userRegisterDTO.email()).
                ifPresent(user -> {
                    throw new EmailAlreadyExistsException("Este e-mail já está associado a uma conta.");
                });

        userRepository.findByUsername(userRegisterDTO.username()).
                ifPresent(user -> {
                    throw new UsernameAlreadyExistsException("Este nome de usuário já está associado a uma conta.");
                });

        LocalDateTime currentDate = LocalDateTime.now();

        User newUser = new User();
        BeanUtils.copyProperties(userRegisterDTO, newUser);
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.password()));
        newUser.setCreatedAt(currentDate);

        User createdUser = userRepository.save(newUser);
        return new UserResponseDTO(createdUser);
    }

}
