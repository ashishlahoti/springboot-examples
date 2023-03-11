package com.example.assertion.service;

import com.example.assertion.dao.UserRepository;
import com.example.assertion.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public Optional<User> getUser(Long userId){
    User user = userRepository.getUserById(userId);
    return Objects.nonNull(user) ? Optional.of(user) : Optional.empty();
  }
}
