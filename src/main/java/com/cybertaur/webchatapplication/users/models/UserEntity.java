package com.cybertaur.webchatapplication.users.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class UserEntity {

    @Id
    private String id;

}
