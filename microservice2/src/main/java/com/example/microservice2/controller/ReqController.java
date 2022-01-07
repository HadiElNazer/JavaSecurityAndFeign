package com.example.microservice2.controller;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
  
import com.example.microservice2.exception.MessageSourceException;

@RestController
public class ReqController {

	@Autowired
	MessageSource messageSource;

	@GetMapping("/getMessageSource/{propreties}")
	//@RequestBody EntityBody entityBody
	public String getMessageSource(@PathVariable String propreties) {
		String message = null;
		try {
			//entityBody.getPropreties()
			message = messageSource.getMessage(propreties, null, null);
		} catch (Exception e) {
			throw new MessageSourceException("notFound");
		}
		return message;
	}
}
