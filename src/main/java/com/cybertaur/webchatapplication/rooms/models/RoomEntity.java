package com.cybertaur.webchatapplication.rooms.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("rooms")
public class RoomEntity {

    @Id
    private String id;
}
