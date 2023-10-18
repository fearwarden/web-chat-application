package com.cybertaur.webchatapplication.rooms;

import com.cybertaur.webchatapplication.rooms.dto.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cybertaur.webchatapplication.users.dto.response.UserDto;
import com.cybertaur.webchatapplication.users.enums.UserAction;
import com.cybertaur.webchatapplication.users.models.UserEntity;
import com.cybertaur.webchatapplication.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final Set<UserDto> onlineUsers = new LinkedHashSet<>();
    private final UserRepository userRepository;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public String handleChatMessage(@Payload String message, SimpMessageHeaderAccessor headerAccessor) {
//        this.simpMessagingTemplate.convertAndSend("/topic/all/messages", message);
//        // TODO: Make exceptions
//        UserEntity user = this.userRepository.findById(message.getSenderId()).orElseThrow(() -> new RuntimeException("User does not exist"));
//
//        if (UserAction.JOINED.equals(message.getAction())) {
//            String userDestination = String.format("/topic/%s/messages", user.getId());
//            onlineUsers.forEach(onlineUser -> {
//                Message newMessage = new Message(user.getId(), null, UserAction.JOINED, null);
//                simpMessagingTemplate.convertAndSend(userDestination, newMessage);
//            });
//            UserDto userDto = new UserDto(user);
//            headerAccessor.getSessionAttributes().put("user", userDto);
//            onlineUsers.add(userDto);
//        }
        System.out.println(message);
        return message;
    }

    @EventListener
    public void handleSessionDisconnectEvent(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();

        if (sessionAttributes == null) {
            logger.error("Unable to get the user as headerAccessor.getSessionAttributes() is null");
            return;
        }
        UserDto userDto = (UserDto) sessionAttributes.get("user");
        if (userDto == null) {
            return;
        }
        onlineUsers.remove(userDto);

        Message message = new Message(userDto.getId(), "", UserAction.LEFT, Instant.now());
        simpMessagingTemplate.convertAndSend("/topic/all/messages", message);
    }
}
