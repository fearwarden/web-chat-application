package com.cybertaur.webchatapplication.rooms.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateRoomDto {
    @NotNull
    @NotBlank(message = "Sender ID is required.")
    private String senderId;
    @NotBlank(message = "Receiver ID is required.")
    @NotNull
    private String receiverId;
}
