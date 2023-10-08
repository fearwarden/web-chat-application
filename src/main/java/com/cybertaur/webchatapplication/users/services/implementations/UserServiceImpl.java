package com.cybertaur.webchatapplication.users.services.implementations;

import com.cybertaur.webchatapplication.users.dto.response.UserDto;
import com.cybertaur.webchatapplication.users.models.UserEntity;
import com.cybertaur.webchatapplication.users.repositories.UserRepository;
import com.cybertaur.webchatapplication.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto register(String email, String password, String username) {
        Optional<UserEntity> optionalUser = this.userRepository.findByEmail(email);

        if (optionalUser.isPresent()) throw new IllegalArgumentException("User exist with email: " + email);

        UserEntity user = new UserEntity();
        String userId = UUID.randomUUID().toString();
        user.setId(userId);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password); // hash the password later when you add the security
        this.userRepository.save(user);

        return new UserDto(user);
    }
}
