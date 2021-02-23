package com.tyss.employeemanagement.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tyss.employeemanagement.service.CustomUserDetailsService;
import com.tyss.employeemanagement.service.JwtUtil;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private CustomUserDetailsService service;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		boolean isAuthenticated = false;
		if (httpServletRequest.getRequestURI().contains("api/v1/employee/authenticate")) {
			isAuthenticated = true;
		} else if (SecurityContextHolder.getContext().getAuthentication() != null) {
			isAuthenticated = true;

		} else {
			String authorizationHeader = httpServletRequest.getHeader("Authorization");

			String token = null;
			String userName = null;

			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				token = authorizationHeader.substring(7);
				userName = jwtUtil.extractUsername(token);
			}

			if (userName != null) {

				UserDetails userDetails = service.loadUserByUsername(userName);

				if (jwtUtil.validateToken(token, userDetails)) {

					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, userDetails.getPassword(), userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
				isAuthenticated = true;

			}

		}

		if (isAuthenticated) {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		} else {
			httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
			httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "credentials not valid");
		}

	}
}
