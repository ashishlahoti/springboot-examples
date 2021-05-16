package com.abc.dao.spec;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.abc.dao.entity.User;
import com.abc.model.query.UserQueryModel;

@Component
public class UserSpecification extends BaseSpecification<User> {

	public Specification<User> findByUserQueryModel(UserQueryModel userQueryModel){
		return findByField("name", userQueryModel.getName());
	}
}
