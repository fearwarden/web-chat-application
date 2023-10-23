package com.cybertaur.webchatapplication.authentication.services;

import com.cybertaur.webchatapplication.users.dto.response.JwtResponseDto;
import com.cybertaur.webchatapplication.users.dto.response.UserDto;

public interface AuthenticationService {
    void register(String email, String password, String username);
    JwtResponseDto login(String email, String password);
}
