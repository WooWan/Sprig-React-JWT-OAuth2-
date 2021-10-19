package com.oauth2.OAuth2.config;

import com.oauth2.OAuth2.jwt.dto.TokenProvider;
import com.oauth2.OAuth2.utils.CookieUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.oauth2.OAuth2.config.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
    private final TokenProvider tokenProvider;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Optional<String> redirectUrl = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(cookie -> cookie.getValue());
        String targetUrl = redirectUrl.orElseThrow(()->  new IllegalStateException("no redirect url"));
        String token = tokenProvider.createToken(authentication);
        String uri = UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("token", token)
                .build().toString();

        getRedirectStrategy().sendRedirect(request, response, uri );
//        super.onAuthenticationSuccess(request, response, authentication);
    }
}
