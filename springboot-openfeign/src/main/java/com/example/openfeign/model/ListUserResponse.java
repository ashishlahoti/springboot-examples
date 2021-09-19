package com.example.openfeign.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ListUserResponse {
    Integer page;

    @JsonProperty("per_page")
    Integer perPage;

    Long total;

    @JsonProperty("total_pages")
    Integer totalPages;

    @JsonProperty("data")
    List<User> users;
}
