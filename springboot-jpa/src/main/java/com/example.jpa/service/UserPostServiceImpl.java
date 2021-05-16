package com.abc.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.abc.dao.entity.Post;

@Service
public class UserPostServiceImpl implements UserPostService {
	
	@Autowired
	private RestTemplate restTemplate;

	public List<Post> getAllPostsByUserId(Long userId) {
		Post[] posts = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/" + userId, Post[].class);
		return Arrays.asList(posts);
	}
}
