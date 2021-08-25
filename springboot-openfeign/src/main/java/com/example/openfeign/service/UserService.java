package com.example.openfeign.service;

import com.example.openfeign.client.UserFeignClient;
import com.example.openfeign.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserFeignClient userFeignClient;

    public User getUserById(Long userId){
        return userFeignClient.getUserById(userId).getData();
    }
}
