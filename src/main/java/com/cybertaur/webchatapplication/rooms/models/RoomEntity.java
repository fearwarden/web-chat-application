package com.cybertaur.webchatapplication.rooms.models;

import com.cybertaur.webchatapplication.rooms.dto.response.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "rooms")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomEntity {

    @Id
    private String id;
    private List<String> users;
    private List<Message> messages;
}
