package com.example.openfeign.service;

import com.example.openfeign.client.UserFeignClient;
import com.example.openfeign.model.User;
import com.example.openfeign.model.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserFeignClient userFeignClient;

    public List<User> getUserList(Integer page) {
        return userFeignClient.getUserList(page).getUsers();
    }

    public List<User> getUserListDelayed(Integer page, Integer delay) {
        return userFeignClient.getUserListDelayed(page, delay).getUsers();
    }

    public User getUserById(Long userId) {
        return userFeignClient.getUserById(userId).getUser();
    }

    public User createUser(UserRequest userRequest){
        return userFeignClient.createUser(userRequest);
    }

    public User updateUser(UserRequest userRequest){
        return userFeignClient.updateUser(userRequest);
    }

    public void deleteUserById(Long userId){
        userFeignClient.deleteUserById(userId);
    }
}
