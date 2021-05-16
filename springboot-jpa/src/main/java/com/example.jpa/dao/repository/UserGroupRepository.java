package com.abc.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.abc.dao.entity.UserGroup;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long>, JpaSpecificationExecutor<UserGroup>{

}
