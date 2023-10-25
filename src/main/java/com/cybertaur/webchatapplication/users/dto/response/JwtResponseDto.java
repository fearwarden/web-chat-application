package com.cybertaur.webchatapplication.users.dto.response;

import lombok.Data;

@Data
public class JwtResponseDto {
    private String id;
    private String accessToken;
    private String refreshToken;
    private String email;
    private String username;
}
