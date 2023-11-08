package com.cybertaur.webchatapplication.rooms.services.implementations;

import com.cybertaur.webchatapplication.rooms.dto.response.Message;
import com.cybertaur.webchatapplication.rooms.dto.response.RoomResponse;
import com.cybertaur.webchatapplication.rooms.models.RoomEntity;
import com.cybertaur.webchatapplication.rooms.repositories.RoomRepository;
import com.cybertaur.webchatapplication.rooms.services.RoomService;
import com.cybertaur.webchatapplication.users.models.UserEntity;
import com.cybertaur.webchatapplication.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final UserService userService;
    @Override
    public void create(String senderId, String receiverId) {
        ArrayList<String> users = new ArrayList<>();
        users.add(senderId);
        users.add(receiverId);
        String roomId = UUID.randomUUID().toString();
        RoomEntity room = new RoomEntity();
        room.setId(roomId);
        room.setUsers(users);
        this.roomRepository.save(room);
    }

    @Override
    public List<RoomResponse> getAllRoomsForUser(UserDetails userDetails) {
        UserEntity user = (UserEntity)
                this.userService.userDetailsService().loadUserByUsername(userDetails.getUsername());
        List<RoomEntity> roomEntities = this.roomRepository.findByUsersContaining(user.getId());
        List<RoomResponse> roomResponses = new ArrayList<>();
        for (RoomEntity room : roomEntities) {
            RoomResponse response = new RoomResponse();
            response.setId(room.getId());
            response.setUsers(room.getUsers());
            response.setMessages(room.getMessages());
            roomResponses.add(response);
        }
        return roomResponses;
    }
}
