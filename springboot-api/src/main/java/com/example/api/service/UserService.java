package com.example.api.service;

import com.example.api.domain.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    Long createUser(User user);

    void updateUser(Long id, User user);

    void deleteUserById(Long id);
}
