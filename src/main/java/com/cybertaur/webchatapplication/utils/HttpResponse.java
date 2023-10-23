package com.cybertaur.webchatapplication.utils;

public record HttpResponse<T>(boolean success, String message, T data) { }
