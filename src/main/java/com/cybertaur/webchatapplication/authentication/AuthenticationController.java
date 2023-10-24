package com.cybertaur.webchatapplication.authentication;

import com.cybertaur.webchatapplication.authentication.dto.request.LoginRequestDto;
import com.cybertaur.webchatapplication.authentication.dto.request.RefreshTokenRequestDto;
import com.cybertaur.webchatapplication.authentication.services.AuthenticationService;
import com.cybertaur.webchatapplication.users.dto.request.RegisterDto;
import com.cybertaur.webchatapplication.users.dto.response.JwtResponseDto;
import com.cybertaur.webchatapplication.utils.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public HttpResponse<Object> register(@RequestBody @Validated RegisterDto body) {
        this.authenticationService.register(body.getEmail(), body.getPassword(), body.getUsername());
        return new HttpResponse<>(true, "User is successfully registered.", null);
    }

    @PostMapping("/login")
    public HttpResponse<JwtResponseDto> login(@RequestBody @Validated LoginRequestDto body) {
        JwtResponseDto loginService = this.authenticationService.login(body.getEmail(), body.getPassword());
        return new HttpResponse<>(true, "User successfully logged in.", loginService);
    }

    //TODO: add endpoint to refresh the token
    @PostMapping("/refresh")
    public HttpResponse<JwtResponseDto> refresh(@RequestBody @Validated RefreshTokenRequestDto body) {
        JwtResponseDto tokens = this.authenticationService.refresh(body.getRefreshToken());
        return new HttpResponse<>(true, "Access token successfully generated.", tokens);
    }
}
