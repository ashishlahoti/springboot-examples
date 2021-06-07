package com.example.jpa.service;

import com.example.jpa.dao.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UserPostServiceImpl implements UserPostService {

    @Autowired
    private RestTemplate restTemplate;

    public List<Post> getAllPostsByUserId(Long userId) {
        Post[] posts = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/" + userId, Post[].class);
        return posts == null ? Collections.emptyList() : Arrays.asList(posts);
    }
}
