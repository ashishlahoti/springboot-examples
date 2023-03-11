package com.example.assertion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
public class User {
    private String firstName;
    private Integer age;
    private Boolean isPremiumUser;
}
