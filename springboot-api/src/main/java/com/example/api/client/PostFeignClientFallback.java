package com.example.api.client;

import com.example.api.domain.Post;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PostFeignClientFallback implements PostFeignClient{
    @Override
    public List<Post> getAllPosts() {
        return Collections.emptyList();
    }

    @Override
    public Post getPostById(Long postId) {
        return null;
    }

    @Override
    public List<Post> getPostByUserId(Long userId) {
        return Collections.emptyList();
    }

    @Override
    public Post createPost(Post post) {
        return null;
    }

    @Override
    public Post updatePost(Post post) {
        return null;
    }

    @Override
    public Post deletePost(Long postId) {
        return null;
    }
}
