package com.abc.service;

import java.util.List;

import com.abc.dao.entity.Post;

public interface UserPostService {

	public List<Post> getAllPostsByUserId(Long userId);
}
