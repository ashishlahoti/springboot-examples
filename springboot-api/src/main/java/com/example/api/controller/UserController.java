package com.example.api.controller;

import com.example.api.domain.Post;
import com.example.api.domain.User;
import com.example.api.service.PostService;
import com.example.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PostService postService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/{userId}/posts")
    public List<Post> getAllPostByUserId(@PathVariable Long userId) {
        return postService.getAllPostsByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable Long userId, User user) {
        userService.updateUser(userId, user);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }
}
