package com.oauth2.OAuth2.jwt.dto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final JwtConfig jwtConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        String jwtToken = getJwtFromRequest(authorization);
        log.info("jwttoken :{}" , jwtToken);
        if (StringUtils.hasText(jwtToken) && tokenProvider.validateToken(jwtToken)) {
            System.out.println("jwtToken = " + jwtToken);
            Claims body = tokenProvider.getClaimsFromToken(jwtToken);
            log.info("claims body: {}", body);
        }
    }

    private String getJwtFromRequest(String authorization) {
        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
            return authorization.substring(7, authorization.length());
        }
        return null;
    }
}
