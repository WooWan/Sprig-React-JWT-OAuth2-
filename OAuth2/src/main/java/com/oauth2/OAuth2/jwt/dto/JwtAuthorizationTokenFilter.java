package com.oauth2.OAuth2.jwt.dto;

import com.oauth2.OAuth2.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
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
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        try {
            String jwtToken = getJwtFromRequest(authorization);
            if (StringUtils.hasText(jwtToken) && tokenProvider.validateToken(jwtToken)) {
                System.out.println("jwtToken = " + jwtToken);
                Claims body = tokenProvider.getClaimsFromToken(jwtToken);
                log.info("claims body: {}", body);
            }
        } catch (ExpiredJwtException jwtException) {
//            access token이 만료되었다면,
            String refreshHeader = request.getHeader("RefreshToken");
            String refreshToken = getJwtFromRequest(refreshHeader);
            Optional<Claims> optionalRefreshBody = Optional.empty();
            try {
                optionalRefreshBody = Optional.of(tokenProvider.getClaimsFromToken(refreshToken));
            } catch (ExpiredJwtException expiredRefreshTokenException) {
                log.info("refresh Token :{} ", refreshToken);
            }
            if (optionalRefreshBody.isPresent()) {
                tokenProvider.createAccessToken();
            }
            String redisData = redisUtil.getData(refreshToken);
//            refresh token 이 유효하다면,access token 재발급
//            tokenProvider.createToken()
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(String authorization) {
        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
            return authorization.substring(7, authorization.length());
        }
        return null;
    }
}
