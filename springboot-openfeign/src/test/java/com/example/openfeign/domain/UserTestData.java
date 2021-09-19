package com.example.openfeign.domain;

import com.example.openfeign.model.User;
import com.example.openfeign.model.SingleUserResponse;

import java.util.List;

public class UserTestData {


    public static User user() {
        return User.builder()
            .id(1L)
            .firstName("George")
            .lastName("Bluth")
            .build();
    }

    public static SingleUserResponse userData() {
        SingleUserResponse singleUserResponse = new SingleUserResponse();
        singleUserResponse.setUser(user());
        return singleUserResponse;
    }

    public static List<User> users() {
        return List.of(user());
    }
}
