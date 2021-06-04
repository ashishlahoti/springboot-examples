package com.example.api.controller;

import com.example.api.domain.Post;
import com.example.api.domain.User;
import com.example.api.service.PostService;
import com.example.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Operation(summary = "Get All Users")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Get User By Id")
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @Operation(summary = "Get All Posts of Given User Id")
    @GetMapping("/{userId}/posts")
    public List<Post> getAllPostByUserId(@PathVariable Long userId) {
        return postService.getAllPostsByUserId(userId);
    }

    @Operation(summary = "Create New User")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @Operation(summary = "Update User")
    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable Long userId, User user) {
        userService.updateUser(userId, user);
    }

    @Operation(summary = "Delete User")
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }
}
