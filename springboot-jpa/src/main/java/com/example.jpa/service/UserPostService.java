package com.example.jpa.service;

import com.example.jpa.dao.entity.Post;

import java.util.List;

public interface UserPostService {

    List<Post> getAllPostsByUserId(Long userId);
}
