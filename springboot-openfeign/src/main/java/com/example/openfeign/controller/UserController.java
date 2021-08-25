package com.example.openfeign.controller;

import com.example.openfeign.model.User;
import com.example.openfeign.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    User getUserById(@PathVariable("userId") Long userId){
        return userService.getUserById(userId);
    }
}
