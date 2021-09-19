package com.example.openfeign.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    Long id;

    String email;

    @JsonProperty("first_name")
    String firstName;

    @JsonProperty("last_name")
    String lastName;

    String avatar;

    String name;

    String job;

    String createdAt;

    String updatedAt;

}
