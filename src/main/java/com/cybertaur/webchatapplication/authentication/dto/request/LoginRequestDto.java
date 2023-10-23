package com.cybertaur.webchatapplication.authentication.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDto {
    @NotNull
    @NotBlank(message = "Email is required.")
    @Email
    private String email;
    @NotBlank(message = "Password is required.")
    @NotNull
    private String password;
}
