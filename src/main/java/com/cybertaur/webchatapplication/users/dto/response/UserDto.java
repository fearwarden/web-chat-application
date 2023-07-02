package com.cybertaur.webchatapplication.users.dto.response;

import com.cybertaur.webchatapplication.users.models.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String email;
    private String username;

    public UserDto(UserEntity user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
    }
}
