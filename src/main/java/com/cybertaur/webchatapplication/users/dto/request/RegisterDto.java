package com.cybertaur.webchatapplication.users.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    @NotNull
    @NotBlank(message = "Email is required.")
    @Email(message = "Email is invalid.")
    private String email;
    @NotBlank(message = "Username is required.")
    @NotNull
    private String username;
    @NotBlank(message = "Password is required.")
    @NotNull
    private String password;
}
