package com.example.demo.service;

import com.example.demo.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private RestTemplate restTemplate;

    public List<Post> getAllPosts() {
        return Arrays.asList(
            new Post(1, "Spring Boot", "All about Spring boot microservice"),
            new Post(2, "Java", "Learn Streams in Java"),
            new Post(3, "JavaScript", "Whats new in ES6")
        );
    }

    public List<Post> getAllPostsByUserId(Long userId) {
        Post[] posts = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/" + userId, Post[].class);
        return Arrays.asList(Objects.requireNonNull(posts));
    }
}
