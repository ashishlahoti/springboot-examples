package com.example.jpa.controller;

import com.example.jpa.dao.entity.User;
import com.example.jpa.model.query.UserQueryModel;
import com.example.jpa.model.request.UserRequestModel;
import com.example.jpa.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Page<User> getAllUsers(UserQueryModel userQueryModel, Pageable pageable) {
        log.debug("getAllUsers: {}, page: {}", userQueryModel, pageable);
        return userService.getAllUsers(userQueryModel, pageable);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id).orElseThrow(() -> new ResourceAccessException("User id not found"));
    }

    @PostMapping
    public User createUser(@RequestBody UserRequestModel userRequestModel) {
        return userService.createUser(userRequestModel);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, UserRequestModel userRequestModel) {
        return userService.updateUser(id, userRequestModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userService.getUserById(id).orElseThrow(() -> new ResourceAccessException("User id not found"));
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
