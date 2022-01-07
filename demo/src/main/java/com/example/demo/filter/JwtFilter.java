package com.example.demo.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.service.JwtService;
import com.example.demo.service.PxpUserDetailsService;

import io.jsonwebtoken.Claims;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private PxpUserDetailsService pxpUserDetailsService;

	@Autowired
	private JwtService jwtService;
	Logger loger = LoggerFactory.getLogger("JwtFilter.class");

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHearder = request.getHeader("Authorization");

		System.out.println("ana hona bl filter");
		System.out.println(authHearder);
		String username = null;
		if (Objects.nonNull(authHearder) && authHearder.startsWith("Bearer ")) {
			System.out.println("ana b2awal if");
			username = jwtService.extractClaim(authHearder.substring(7), Claims::getSubject);
			loger.info(username);
		}
		System.out.println("5elast mn if");
		System.out.println(username);
		if (Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
			UserDetails userDetails = pxpUserDetailsService.loadUserByUsername(username);
			System.out.println("ana hona");
			if (jwtService.isValidToken(authHearder.substring(7), userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
