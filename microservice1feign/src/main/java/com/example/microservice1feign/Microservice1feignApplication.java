package com.example.microservice1feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Microservice1feignApplication {

	public static void main(String[] args) {
		SpringApplication.run(Microservice1feignApplication.class, args);
	}

}
