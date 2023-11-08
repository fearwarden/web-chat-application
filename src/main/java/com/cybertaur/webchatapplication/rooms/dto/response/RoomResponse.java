package com.cybertaur.webchatapplication.rooms.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class RoomResponse {
    private String id;
    private List<String> users;
    private List<Message> messages;
}
