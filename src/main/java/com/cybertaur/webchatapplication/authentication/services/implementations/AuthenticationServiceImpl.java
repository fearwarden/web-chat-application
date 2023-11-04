package com.cybertaur.webchatapplication.authentication.services.implementations;

import com.cybertaur.webchatapplication.authentication.exceptions.throwables.RefreshTokenNotFoundException;
import com.cybertaur.webchatapplication.authentication.services.AuthenticationService;
import com.cybertaur.webchatapplication.authentication.services.JwtService;
import com.cybertaur.webchatapplication.users.dto.response.JwtResponseDto;
import com.cybertaur.webchatapplication.users.exceptions.throwables.UserNotFoundException;
import com.cybertaur.webchatapplication.users.models.TokenEntity;
import com.cybertaur.webchatapplication.users.models.UserEntity;
import com.cybertaur.webchatapplication.users.repositories.TokenRepository;
import com.cybertaur.webchatapplication.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(String email, String password, String username) {
        //TODO: add confirmation password and check if they matches
        Optional<UserEntity> optionalUser = this.userRepository.findByEmail(email);

        if (optionalUser.isPresent()) throw new IllegalArgumentException("User exist with email: " + email);

        UserEntity user = new UserEntity();
        String userId = UUID.randomUUID().toString();
        user.setId(userId);
        user.setEmail(email);
        user.setName(username);
        user.setPassword(this.passwordEncoder.encode(password));
        this.userRepository.save(user);

        TokenEntity refreshToken = new TokenEntity();
        String refreshTokenId = UUID.randomUUID().toString();
        refreshToken.setId(refreshTokenId);
        refreshToken.setRefreshToken(this.jwtService.generateRefreshToken());
        refreshToken.setUserId(userId);
        this.tokenRepository.save(refreshToken);
    }

    @Override
    public JwtResponseDto login(String email, String password) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        UserEntity user = this.userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        String accessToken = this.jwtService.generateToken(user);
        TokenEntity token = this.tokenRepository.findByUserId(user.getId());
        JwtResponseDto jwtResponse = new JwtResponseDto();
        jwtResponse.setAccessToken(accessToken);
        jwtResponse.setRefreshToken(token.getRefreshToken());
        jwtResponse.setEmail(user.getEmail());
        jwtResponse.setUsername(user.getName());
        jwtResponse.setId(user.getId());
        return jwtResponse;
    }

    @Override
    public JwtResponseDto refresh(String refreshToken) {
        TokenEntity token = this.tokenRepository.findByRefreshToken(refreshToken).orElseThrow(RefreshTokenNotFoundException::new);
        UserEntity user = this.userRepository.findById(token.getUserId()).orElseThrow(UserNotFoundException::new);

        String accessToken = this.jwtService.generateToken(user);
        String newRefreshToken = this.jwtService.generateRefreshToken();
        token.setRefreshToken(newRefreshToken);
        this.tokenRepository.save(token);

        JwtResponseDto newToken = new JwtResponseDto();
        newToken.setRefreshToken(newRefreshToken);
        newToken.setAccessToken(accessToken);
        newToken.setEmail(user.getEmail());
        newToken.setUsername(user.getName());
        newToken.setId(user.getId());
        return newToken;
    }
}
