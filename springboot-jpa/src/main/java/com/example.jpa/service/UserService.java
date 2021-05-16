package com.abc.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.abc.dao.entity.User;
import com.abc.model.query.UserQueryModel;
import com.abc.model.request.UserRequestModel;

public interface UserService {

	public Page<User> getAllUsers(UserQueryModel userQueryModel, Pageable pageable);
	public Optional<User> getUserById(Long id);
	public User createUser(UserRequestModel userRequestModel);
	public User updateUser(Long id, UserRequestModel userRequestModel);
	public void deleteUserById(Long id);
}
