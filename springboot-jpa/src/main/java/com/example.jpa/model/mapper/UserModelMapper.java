package com.abc.model.mapper;

import org.mapstruct.Mapper;

import com.abc.dao.entity.User;
import com.abc.model.request.UserRequestModel;

@Mapper(componentModel="spring")
public interface UserModelMapper {

	public User toUserEntity(UserRequestModel userRequestModel);
	
}
