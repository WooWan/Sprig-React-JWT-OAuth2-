package com.oauth2.OAuth2.jwt.dto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenProvider {

    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public String createToken(Authentication authentication){
        LocalDate expirationDays = LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays());

        return Jwts.builder().setSubject("subject")
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(expirationDays))
                .signWith(secretKey)
                .compact();
    }

    public Claims getClaimsFromToken(String jwtToken) {
        Claims body = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
        return body;
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(jwtToken);
            return true;
        } catch (Exception exception) {
            log.error("invalid jwt error");
        }
        return false;
    }
}
