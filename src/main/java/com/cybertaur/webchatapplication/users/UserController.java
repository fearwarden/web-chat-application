package com.cybertaur.webchatapplication.users;

import com.cybertaur.webchatapplication.users.dto.request.RegisterDto;
import com.cybertaur.webchatapplication.users.dto.response.UserDto;
import com.cybertaur.webchatapplication.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto register(@RequestBody RegisterDto body) {
        return this.userService.register(body.getEmail(), body.getPassword(), body.getUsername());
    }

    @GetMapping("/{id}")
    public UserDto findUserById(@PathVariable(name = "id") String id) {
        return this.userService.findUserById(id);
    }
}
