package com.example.api.client;

import com.example.api.config.FeignClientConfig;
import com.example.api.domain.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "postFeignClient",
        url = "${client.post.baseUrl}",
        configuration = FeignClientConfig.class,
        fallback = PostFeignClientFallback.class)
public interface PostFeignClient {
    @GetMapping("/posts")
    List<Post> getAllPosts();

    @GetMapping("/posts/{postId}")
    Post getPostById(@PathVariable Long postId);

    @GetMapping("/posts")
    List<Post> getPostByUserId(@RequestParam Long userId);

    @PostMapping("/posts")
    Post createPost(Post post);

    @PutMapping("/posts")
    Post updatePost(Post post);

    @DeleteMapping("/posts/{postId}")
    Post deletePost(@PathVariable Long postId);
}
