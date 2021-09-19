package com.example.openfeign.controller;

import com.example.openfeign.model.User;
import com.example.openfeign.model.UserRequest;
import com.example.openfeign.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    List<User> getUserList(@RequestParam("page") Integer page){
        return userService.getUserList(page);
    }

    @GetMapping("/{userId}")
    User getUserById(@PathVariable("userId") Long userId){
        return userService.getUserById(userId);
    }

    @PostMapping
    User createUser(@RequestBody UserRequest userRequest){
        return userService.createUser(userRequest);
    }

    @PutMapping
    User updateUser(@RequestBody UserRequest userRequest){
        return userService.updateUser(userRequest);
    }

    @DeleteMapping("/{userId}")
    void deleteUserById(@PathVariable("userId") Long userId){
        userService.deleteUserById(userId);
    }
}
