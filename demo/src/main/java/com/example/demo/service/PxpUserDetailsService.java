package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repositry.UserRepositry;

@Service
public class PxpUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepositry userRepositry;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> user = userRepositry.findByUserName(username);
		if (user.isPresent()) {
			List<SimpleGrantedAuthority> list = new ArrayList<>();
			SimpleGrantedAuthority s = new SimpleGrantedAuthority("hadi");
			list.add(s);
			System.out.println("ana fi pxp");
			return new User(user.get().getUserName(), user.get().getPassword(), list);
		} else {
			throw new UsernameNotFoundException("not Found");
		}
	}

}
