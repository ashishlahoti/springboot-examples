package com.example.api.client;

import com.example.api.domain.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "commentFeignClient", url = "https://jsonplaceholder.typicode.com/comments")
public interface CommentFeignClient {

    @GetMapping
    List<Comment> getAllComments();

    @GetMapping("/{commentId}")
    Comment getCommentById(@PathVariable Long commentId);

    @GetMapping
    List<Comment> getCommentsByPostId(@RequestParam Long postId);

    @PostMapping
    Comment createComment(Comment comment);

    @PutMapping
    Comment updateComment(Comment comment);

    @DeleteMapping("/{commentId}")
    Comment deleteComment(@PathVariable Long commentId);
}
