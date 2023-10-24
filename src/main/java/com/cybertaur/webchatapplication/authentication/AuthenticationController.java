package com.cybertaur.webchatapplication.authentication;

import com.cybertaur.webchatapplication.authentication.dto.request.LoginRequestDto;
import com.cybertaur.webchatapplication.authentication.services.AuthenticationService;
import com.cybertaur.webchatapplication.users.dto.request.RegisterDto;
import com.cybertaur.webchatapplication.users.dto.response.JwtResponseDto;
import com.cybertaur.webchatapplication.utils.HttpResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public HttpResponse<JwtResponseDto> login(@RequestBody @Validated LoginRequestDto body, HttpServletResponse response) {
        JwtResponseDto tokens = this.authenticationService.login(body.getEmail(), body.getPassword());
        this.cookieManager("accessToken", tokens.getAccessToken(), response);
        this.cookieManager("refreshToken", tokens.getRefreshToken(), response);
        return new HttpResponse<>(true, "User successfully logged in.", tokens);
    }

    //TODO: add endpoint to refresh the token
    @PostMapping("/refresh")
    public HttpResponse<JwtResponseDto> refresh(@CookieValue("refreshToken") String refreshToken, HttpServletResponse response) {
        JwtResponseDto tokens = this.authenticationService.refresh(refreshToken);
        this.cookieManager("accessToken", tokens.getAccessToken(), response);
        this.cookieManager("refreshToken", tokens.getRefreshToken(), response);
        return new HttpResponse<>(true, "Access token successfully generated.", tokens);
    }

    private <T> void cookieManager(String name, T data, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, data.toString());
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
