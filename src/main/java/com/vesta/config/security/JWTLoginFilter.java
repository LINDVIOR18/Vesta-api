package com.vesta.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vesta.repositry.AccountCredentials;
import com.vesta.services.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private TokenService tokenService;

    public JWTLoginFilter(String url, AuthenticationManager authenticationManager) {

        super(new AntPathRequestMatcher(url));

        setAuthenticationManager(authenticationManager);

        tokenService = new TokenService();

    }

    @Override

    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)

            throws AuthenticationException, IOException, ServletException {

        AccountCredentials credentials = new ObjectMapper().readValue(httpServletRequest.getInputStream(), AccountCredentials.class);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());

        return getAuthenticationManager().authenticate(token);

    }

    @Override

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication)

            throws IOException, ServletException {

        String name = authentication.getName();

//        tokenService.addAuthentication(response, name);

    }
}