package com.example.microservice1feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservice1feign.entity.EntityBody;
import com.example.microservice1feign.util.FeignServiceUtil;

@RestController
public class FeignClientController {
	@Autowired
	FeignServiceUtil feignServiceUtil;

	@GetMapping("/info")
	public String fiegnMessage(@RequestBody EntityBody entityBody) {
		return feignServiceUtil.getMessageSource(entityBody.getProperties());
	}
}
