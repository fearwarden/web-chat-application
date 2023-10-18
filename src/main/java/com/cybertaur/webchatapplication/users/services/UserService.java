package com.cybertaur.webchatapplication.users.services;

import com.cybertaur.webchatapplication.users.dto.response.UserDto;

public interface UserService {
    UserDto register(String email, String password, String username);
    UserDto findUserById(String id);
}
