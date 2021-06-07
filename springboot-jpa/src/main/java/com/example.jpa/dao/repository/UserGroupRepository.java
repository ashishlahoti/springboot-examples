package com.example.jpa.dao.repository;

import com.example.jpa.dao.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long>, JpaSpecificationExecutor<UserGroup> {

}
