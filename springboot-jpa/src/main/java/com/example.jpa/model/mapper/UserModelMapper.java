package com.example.jpa.model.mapper;

import com.example.jpa.dao.entity.User;
import com.example.jpa.model.request.UserRequestModel;
import org.springframework.stereotype.Component;

@Component
public class UserModelMapper {

    public User toUserEntity(UserRequestModel userRequestModel){
        User user = new User();
        user.setName(userRequestModel.getName());
        user.setAge(userRequestModel.getAge());
        return user;
    }
}
