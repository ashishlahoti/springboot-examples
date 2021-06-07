package com.example.jpa.service;

import com.example.jpa.dao.entity.User;
import com.example.jpa.model.query.UserQueryModel;
import com.example.jpa.model.request.UserRequestModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    Page<User> getAllUsers(UserQueryModel userQueryModel, Pageable pageable);

    Optional<User> getUserById(Long id);

    User createUser(UserRequestModel userRequestModel);

    User updateUser(Long id, UserRequestModel userRequestModel);

    void deleteUserById(Long id);
}
