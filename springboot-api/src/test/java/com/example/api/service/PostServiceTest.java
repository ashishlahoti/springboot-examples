package com.example.api.service;

import com.example.api.client.PostFeignClient;
import com.example.api.domain.Post;
import com.example.api.domain.PostTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PostServiceTest {

    @MockBean
    private PostFeignClient postFeignClient;

    @Autowired
    private PostService postService;

    @Test
    public void getAllPosts_whenValidClientResponse_returnAllPosts() {
        when(postFeignClient.getAllPosts()).thenReturn(PostTestData.posts());

        List<Post> posts = postService.getAllPosts();

        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts.get(0).getId()).isEqualTo(1);
        assertThat(posts.get(0).getUserId()).isEqualTo(1);
        assertThat(posts.get(0).getTitle()).isEqualTo("title");
        assertThat(posts.get(0).getBody()).isEqualTo("body");
    }


    @Test
    public void getPostById_whenValidPostId_returnThatPost() {
        when(postFeignClient.getPostById(anyLong())).thenReturn(PostTestData.post());

        Post post = postService.getPostById(1L);

        assertThat(post.getId()).isEqualTo(1);
        assertThat(post.getUserId()).isEqualTo(1);
        assertThat(post.getTitle()).isEqualTo("title");
        assertThat(post.getBody()).isEqualTo("body");
    }

    @Test
    public void getAllPostsByUserId_whenValidUserId_returnAllPostsOfThatUser() {
        when(postFeignClient.getPostByUserId(anyLong())).thenReturn(PostTestData.posts());

        List<Post> posts = postService.getAllPostsByUserId(1L);

        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts.get(0).getId()).isEqualTo(1);
        assertThat(posts.get(0).getUserId()).isEqualTo(1);
        assertThat(posts.get(0).getTitle()).isEqualTo("title");
        assertThat(posts.get(0).getBody()).isEqualTo("body");
    }

    @Test
    public void createPost_whenValidPostData_createAndReturnThatPost() {
        when(postFeignClient.createPost(PostTestData.post())).thenReturn(PostTestData.post());

        Post post = postService.createPost(PostTestData.post());

        assertThat(post.getId()).isEqualTo(1);
        assertThat(post.getUserId()).isEqualTo(1);
        assertThat(post.getTitle()).isEqualTo("title");
        assertThat(post.getBody()).isEqualTo("body");
    }
}
