package com.cybertaur.webchatapplication.rooms;

import com.cybertaur.webchatapplication.rooms.dto.request.CreateRoomDto;
import com.cybertaur.webchatapplication.rooms.dto.response.Message;
import com.cybertaur.webchatapplication.rooms.dto.response.RoomResponse;
import com.cybertaur.webchatapplication.rooms.services.RoomService;
import com.cybertaur.webchatapplication.utils.HttpResponse;
import com.cybertaur.webchatapplication.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final UserRepository userRepository;
    private final RoomService roomService;

    @PostMapping
    public HttpResponse<Object> create(@RequestBody @Validated CreateRoomDto body) {
        this.roomService.create(body.getSenderId(), body.getReceiverId());
        return new HttpResponse<>(true, "Room is successfully created.", null);
    }

    @GetMapping
    public HttpResponse<List<RoomResponse>> getAllRoomsForUser(@AuthenticationPrincipal UserDetails userDetails) {
        List<RoomResponse> responses = this.roomService.getAllRoomsForUser(userDetails);
        return new HttpResponse<>(true, "Rooms successfully fetched.", responses);
    }

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message) {
        this.simpMessagingTemplate.convertAndSendToUser(message.getReceiverId(), "/private", message);
        System.out.println(message.toString());
        return message;
    }
}
