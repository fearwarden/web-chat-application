package com.cybertaur.webchatapplication.users;

import com.cybertaur.webchatapplication.users.dto.request.RegisterDto;
import com.cybertaur.webchatapplication.users.dto.response.UserDto;
import com.cybertaur.webchatapplication.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto register(@RequestBody RegisterDto body) {
        return this.userService.register(body.getEmail(), body.getPassword(), body.getUsername());
    }
}
