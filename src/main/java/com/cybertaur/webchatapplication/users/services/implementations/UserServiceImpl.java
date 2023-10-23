package com.cybertaur.webchatapplication.users.services.implementations;

import com.cybertaur.webchatapplication.users.dto.response.UserDto;
import com.cybertaur.webchatapplication.users.exceptions.throwables.UserNotFoundException;
import com.cybertaur.webchatapplication.users.models.UserEntity;
import com.cybertaur.webchatapplication.users.repositories.UserRepository;
import com.cybertaur.webchatapplication.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);
            }
        };
    }

    @Override
    public UserDto findUserById(String id) {
        UserEntity user = this.userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return new UserDto(user);
    }
}
