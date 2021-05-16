package com.example.api.controller;

import com.example.api.domain.Comment;
import com.example.api.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/{commentId}")
    public Comment getCommentById(@PathVariable Long commentId) {
        return commentService.getCommentById(commentId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(Comment comment) {
        return commentService.createComment(comment);
    }

    @PutMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateComment(@PathVariable Long commentId, Comment comment) {
        commentService.updateComment(commentId, comment);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
