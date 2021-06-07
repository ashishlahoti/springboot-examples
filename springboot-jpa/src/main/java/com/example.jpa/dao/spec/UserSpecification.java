package com.example.jpa.dao.spec;

import com.example.jpa.dao.entity.User;
import com.example.jpa.model.query.UserQueryModel;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserSpecification extends BaseSpecification<User> {

	public Specification<User> findByUserQueryModel(UserQueryModel userQueryModel) {
		return findByField("name", userQueryModel.getName());
	}
}
