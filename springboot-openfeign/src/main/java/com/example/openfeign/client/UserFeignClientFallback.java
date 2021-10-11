package com.example.openfeign.client;

import com.example.openfeign.model.ListUserResponse;
import com.example.openfeign.model.SingleUserResponse;
import com.example.openfeign.model.User;
import com.example.openfeign.model.UserRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFeignClientFallback implements UserFeignClient {

    @Override
    public ListUserResponse getUserList(Integer page) {
        return null;
    }

    @Override
    public ListUserResponse getUserListDelayed(Integer page, Integer delay) {
        ListUserResponse listUserResponse = new ListUserResponse();
        listUserResponse.setUsers(List.of(User.builder()
            .id(1L)
            .firstName("George")
            .lastName("Bluth")
            .build()));
        return listUserResponse;
    }

    @Override
    public SingleUserResponse getUserById(Long userId) {
        return null;
    }

    @Override
    public User createUser(UserRequest userRequest) {
        return null;
    }

    @Override
    public User updateUser(UserRequest userRequest) {
        return null;
    }

    @Override
    public void deleteUserById(Long userId) {

    }
}
