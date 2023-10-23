package com.cybertaur.webchatapplication.users.services;

import com.cybertaur.webchatapplication.users.dto.response.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();
    UserDto findUserById(String id);
}
