package com.example.api.service;

import com.example.api.client.CommentFeignClient;
import com.example.api.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentFeignClient commentFeignClient;

    @Override
    public List<Comment> getAllComments() {
        return commentFeignClient.getAllComments();
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentFeignClient.getCommentById(id);
    }

    @Override
    public List<Comment> getAllCommentsByPostId(Long postId) {
        return commentFeignClient.getCommentsByPostId(postId);
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentFeignClient.createComment(comment);
    }

    @Override
    public void updateComment(Long commentId, Comment comment) {
        commentFeignClient.updateComment(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentFeignClient.deleteComment(commentId);
    }

}
