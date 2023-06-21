package com.cybertaur.webchatapplication.users.repositories;

import com.cybertaur.webchatapplication.users.models.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {
}
