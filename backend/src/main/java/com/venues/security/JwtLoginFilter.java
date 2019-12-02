package com.venues.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.venues.model.security.UserCredentials;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.venues.security.TokenProvider.addTokenToResponse;
import static com.venues.security.TokenProvider.generateToken;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JwtLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException {

        UserCredentials creds = new ObjectMapper()
                .readValue(req.getInputStream(), UserCredentials.class);

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        creds.getUsername(),
                        creds.getPassword()
                )
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req, HttpServletResponse res,
            FilterChain chain, Authentication auth) {

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String userRole = null;

        for (GrantedAuthority authority : userDetails.getAuthorities()) {

            userRole = authority.getAuthority();
            if (!(userRole == null))
                break;
        }

        String token = generateToken(auth.getName(), userRole);
        addTokenToResponse(res,token);
    }

}
