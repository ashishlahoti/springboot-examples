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
    List<User> getUserListDelayed(@RequestParam("page") Integer page, @RequestParam(name = "delay", defaultValue = "0") Integer delay){
        return userService.getUserListDelayed(page, delay);
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
