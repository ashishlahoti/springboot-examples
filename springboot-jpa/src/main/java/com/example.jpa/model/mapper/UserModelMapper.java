package com.example.jpa.model.mapper;

import com.example.jpa.dao.entity.User;
import com.example.jpa.model.request.UserRequestModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserModelMapper {

    User toUserEntity(UserRequestModel userRequestModel);

}
