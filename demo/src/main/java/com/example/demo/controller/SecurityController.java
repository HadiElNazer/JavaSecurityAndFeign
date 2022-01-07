package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserEntity;
import com.example.demo.model.AuthRequest;
import com.example.demo.service.JwtService;
import com.example.demo.service.PxpUserDetailsService;
import com.example.demo.service.UserServcice;

import ch.qos.logback.classic.net.SyslogAppender;

@RestController
public class SecurityController {
	@Value("${SECRET_KEY}")
	private String info;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PxpUserDetailsService pxpUserDetailsService;

	@Autowired
	private UserServcice userService;

	@Autowired
	private JwtService jwtService;

	@RequestMapping(value = "info", method = RequestMethod.GET)
	public String info() {
		return info;
	}

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String hello() {
		return "helllo";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public UserEntity createUser(@RequestBody AuthRequest authrequest) {  
		return userService.insertOne(authrequest);
	}

	@RequestMapping(value = "authenticate", method = RequestMethod.POST)
	public String getJwt(@RequestBody AuthRequest authrequest) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authrequest.getUserName(), authrequest.getPassword()));
		} catch (Exception e) {
			throw e;
		}
		UserDetails userDetails = pxpUserDetailsService.loadUserByUsername(authrequest.getUserName());
		System.out.println(userDetails.getPassword());
		System.out.println(userDetails.getAuthorities());

		final String jwt = jwtService.getJWT(userDetails);
		return jwt;

	}
}
