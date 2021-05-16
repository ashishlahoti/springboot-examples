package com.example.api;

import com.example.api.controller.CommentController;
import com.example.api.controller.PostController;
import com.example.api.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApiApplicationTests {

    @Autowired
    private UserController userController;

    @Autowired
    private PostController postController;

    @Autowired
    private CommentController commentController;

    @Test
    void contextLoads() {
        assertThat(userController).isNotNull();
        assertThat(postController).isNotNull();
        assertThat(commentController).isNotNull();
    }

}
