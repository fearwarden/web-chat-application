package com.cybertaur.webchatapplication.rooms.repositories;

import com.cybertaur.webchatapplication.rooms.models.RoomEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<RoomEntity, String> {
}
