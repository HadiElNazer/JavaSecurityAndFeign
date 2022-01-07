package com.example.demo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	@Value("${SECRET_KEY}")
	private String secret;

	public String getJWT(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		String jwt = Jwts.builder()
				.setClaims(claims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+360000))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
		return jwt;
	}

	public Boolean isValidToken(String token, UserDetails userdetails) {
		System.out.println("ana bl validation");
		final String username = extractClaim(token, Claims::getSubject);
		return (username.equals(userdetails.getUsername())
				&& !extractClaim(token, Claims::getExpiration).before(new Date()));
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		System.out.println("ana bl explain1");
		System.out.println(token);
		Claims claim = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		System.out.println("ana bl explain2");
		return claimsResolver.apply(claim);
	}

}
