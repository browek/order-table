package com.table.order.security;

import static com.table.order.security.TokenProvider.addTokenToResponse;
import static com.table.order.security.TokenProvider.generateToken;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.table.order.model.security.UserCredentials;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

	public JwtLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		UserCredentials creds = new ObjectMapper()
				.readValue(req.getInputStream(), UserCredentials.class);

		return getAuthenticationManager()
				.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) {

		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		String userRole = null;

		for (GrantedAuthority authority : userDetails.getAuthorities()) {

			userRole = authority.getAuthority();
			if (userRole != null)
				break;
		}

		String token = generateToken(auth.getName(), userRole);
		addTokenToResponse(res, token);
	}

}
