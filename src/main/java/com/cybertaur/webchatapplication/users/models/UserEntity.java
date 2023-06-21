package com.cybertaur.webchatapplication.users.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class UserEntity {

    @Id
    private String id;
    private String email;
    private String username;
    private String password;
    private String profileImage;

}
