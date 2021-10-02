package com.oauth2.OAuth2.jwt.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@Getter @Setter
public class JwtConfig {

    private final Auth auth = new Auth();

    @Getter @Setter
    public static class Auth {
        private String tokenSecret;
        private long tokenExpirationDays;
    }
}
