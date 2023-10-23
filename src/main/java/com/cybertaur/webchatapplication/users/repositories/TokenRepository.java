package com.cybertaur.webchatapplication.users.repositories;

import com.cybertaur.webchatapplication.users.models.TokenEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TokenRepository extends MongoRepository<TokenEntity, String> {
    TokenEntity findByUserId(String userId);
    Optional<TokenEntity> findByRefreshToken(String refreshToken);
}
