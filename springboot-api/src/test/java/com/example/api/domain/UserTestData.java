package com.example.api.domain;

import java.time.LocalDate;
import java.util.List;

public class UserTestData {

    public static User user(){
        return User.builder()
                .id(1L)
                .name( "Adam")
                .dateOfBirth(LocalDate.of(1986, 8, 22))
                .build();
    }

    public static List<User> users() {
        return List.of(user());
    }
}
