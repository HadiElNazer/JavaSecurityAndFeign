package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.model.AuthRequest;
import com.example.demo.repositry.UserRepositry;

@Service
public class UserServcice {

	@Autowired
	private UserRepositry userRepositry;

	public UserEntity insertOne(AuthRequest authrequest) {
		UserEntity user = new UserEntity();
		user.setUserName(authrequest.getUserName());
		user.setPassword(authrequest.getPassword());
		return userRepositry.save(user);
	}

}
