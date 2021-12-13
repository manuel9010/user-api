package com.manudev90.userapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.manudev90.userapi.model.entity.PhoneEntity;



public interface PhoneRepository  extends JpaRepository<PhoneEntity, Long>,  QueryByExampleExecutor<PhoneEntity> {

}
