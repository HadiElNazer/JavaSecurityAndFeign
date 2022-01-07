package com.example.demo.repositry;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserEntity;

@Repository
public interface UserRepositry extends MongoRepository<UserEntity, String> {
	public Optional<UserEntity> findByUserName(String userName);
}
