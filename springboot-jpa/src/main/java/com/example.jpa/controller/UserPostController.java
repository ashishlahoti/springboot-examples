package com.abc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.dao.entity.Post;
import com.abc.service.UserPostService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/userpost")
public class UserPostController {
	
	@Autowired
	private UserPostService userPostService;
	
	@GetMapping("/{userId}")
	public List<Post> getAllPostsByUserId(@PathVariable Long userId) {
		log.debug("getAllPostsByUserId", userId);
		return userPostService.getAllPostsByUserId(userId);
	}
}
