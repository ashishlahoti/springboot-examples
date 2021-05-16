package com.example.api.service;

import com.example.api.domain.Post;

import java.util.List;

public interface PostService {

	List<Post> getAllPosts();

	Post getPostById(Long postId);
	
	List<Post> getAllPostsByUserId(Long userId);

	Post createPost(Post post);

	void updatePost(Long postId, Post post);

	void deletePost(Long postId);
}
