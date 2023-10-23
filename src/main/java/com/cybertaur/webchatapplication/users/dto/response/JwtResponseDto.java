package com.cybertaur.webchatapplication.users.dto.response;

import lombok.Data;

@Data
public class JwtResponseDto {
    private String accessToken;
    private String refreshToken;
}
