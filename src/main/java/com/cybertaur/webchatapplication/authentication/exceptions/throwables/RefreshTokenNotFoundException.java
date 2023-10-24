package com.cybertaur.webchatapplication.authentication.exceptions.throwables;

public class RefreshTokenNotFoundException extends RuntimeException {
    public RefreshTokenNotFoundException() {
        super("Refresh Token not found.");
    }
}
