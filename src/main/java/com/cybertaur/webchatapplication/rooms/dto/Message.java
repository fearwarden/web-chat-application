package com.cybertaur.webchatapplication.rooms.dto;

import com.cybertaur.webchatapplication.users.enums.UserAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String senderId;
    private String content;
    private UserAction action;
    private Instant timestamp;
}
