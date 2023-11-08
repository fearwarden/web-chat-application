package com.cybertaur.webchatapplication.rooms.repositories;

import com.cybertaur.webchatapplication.rooms.models.RoomEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends MongoRepository<RoomEntity, String> {
    boolean findByUsers(List<String> users);
    List<RoomEntity> findByUsersContaining(String userId);
}
