package com.example.openfeign.domain;

import com.example.openfeign.model.User;
import com.example.openfeign.model.UserData;

import java.time.LocalDate;
import java.util.List;

public class UserTestData {


    public static User user() {
        return User.builder()
            .id(1L)
            .firstName("Adam")
            .build();
    }

    public static UserData userData() {
        return new UserData();
    }

    public static List<User> users() {
        return List.of(user());
    }
}
