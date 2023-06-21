package com.cybertaur.webchatapplication.rooms.dto;

import lombok.Data;

@Data
public class Message {
    private String senderId;
    private String content;
    private String timestamp;
}
