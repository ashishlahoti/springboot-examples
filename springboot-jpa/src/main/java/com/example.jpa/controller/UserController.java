package com.abc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.dao.entity.User;
import com.abc.model.query.UserQueryModel;
import com.abc.model.request.UserRequestModel;
import com.abc.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public Page<User> getAllUsers(UserQueryModel userQueryModel, Pageable pageable) {
		log.debug("getAllUsers", userQueryModel, pageable);
		return userService.getAllUsers(userQueryModel, pageable);
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		return userService.getUserById(id).orElseThrow(() -> new ResourceNotFoundException("User id not found"));
	}
	
	@PostMapping
	public User createUser(UserRequestModel userRequestModel) {
		return userService.createUser(userRequestModel);
	}
	
	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id, UserRequestModel userRequestModel) {
		return userService.updateUser(id, userRequestModel);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable Long id){
		userService.getUserById(id).orElseThrow(() -> new ResourceNotFoundException("User id not found"));
		userService.deleteUserById(id);
		return ResponseEntity.ok().build();
	}
}
