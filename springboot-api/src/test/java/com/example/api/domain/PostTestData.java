package com.example.api.domain;

import java.util.List;

public class PostTestData {

    public static Post post(){
        return new Post(1L ,1L, "title", "body");
    }

    public static List<Post> posts() {
        return List.of(post());
    }
}
