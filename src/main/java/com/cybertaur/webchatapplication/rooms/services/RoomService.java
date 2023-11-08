package com.cybertaur.webchatapplication.rooms.services;

import com.cybertaur.webchatapplication.rooms.dto.response.RoomResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface RoomService {
    void create(String senderId, String receiverId);
    List<RoomResponse> getAllRoomsForUser(UserDetails userDetails);
}
