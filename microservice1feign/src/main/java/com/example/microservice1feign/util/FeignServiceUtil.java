package com.example.microservice1feign.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "feignDemo", url = "http://localhost:8586")
public interface FeignServiceUtil {

	@RequestMapping(value = "/getMessageSource/{propreties}", method = RequestMethod.GET)
	public String getMessageSource(@PathVariable String propreties); 
}
