package com.vesta.service.impl;

import com.vesta.controller.view.Token;
import com.vesta.service.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.vesta.config.security.SecurityConstants.*;

@Service
public class TokenServiceImpl implements TokenService {

    public Token generatedToken(String username) {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
        return new Token(TOKEN_PREFIX + JWT);
    }

    public Token generatedRefreshToken(String username) {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, REFRESH_SECRET)
                .compact();
        return new Token(TOKEN_PREFIX + JWT);
    }

    public String getSubject(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);

        if (token != null) {
            return Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
        }
        return null;
    }
}