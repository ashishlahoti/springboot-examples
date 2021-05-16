package com.example.api.controller;

import com.example.api.domain.Comment;
import com.example.api.domain.Post;
import com.example.api.service.CommentService;
import com.example.api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @GetMapping("/{postId}/comments")
    public List<Comment> getAllCommentsByPostId(@PathVariable Long postId) {
        return commentService.getAllCommentsByPostId(postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(Post post) {
        return postService.createPost(post);
    }

    @PutMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(@PathVariable Long postId, Post post) {
        postService.updatePost(postId, post);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }
}
