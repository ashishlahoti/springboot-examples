package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@GetMapping("/posts")
	public List<Post> getAllPosts() {
		return postService.getAllPosts();
	}

	@GetMapping("/posts/user/{userId}")
	public List<Post> getAllPostsByUserId(@PathVariable Long userId) {
		return postService.getAllPostsByUserId(userId);
	}
}
