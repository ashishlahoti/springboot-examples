package com.example.assertion.model;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class AdminUser extends User {
    private Boolean isAdmin;
}
