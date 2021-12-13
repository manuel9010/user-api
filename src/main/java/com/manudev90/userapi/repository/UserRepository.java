package com.manudev90.userapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.manudev90.userapi.model.entity.UserEntity;


public interface UserRepository  extends JpaRepository<UserEntity, Long>,  QueryByExampleExecutor<UserEntity>{
	

}
