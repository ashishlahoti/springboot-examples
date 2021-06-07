package com.example.demo.service;

import com.example.demo.model.Post;

import java.util.List;

public interface PostService {

    List<Post> getAllPosts();

    List<Post> getAllPostsByUserId(Long userId);
}
