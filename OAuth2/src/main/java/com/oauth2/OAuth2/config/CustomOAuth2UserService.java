package com.oauth2.OAuth2.config;

import com.oauth2.OAuth2.config.provider.GithubUserInfo;
import com.oauth2.OAuth2.config.provider.GoogleUserInfo;
import com.oauth2.OAuth2.config.provider.KakaoUserInfo;
import com.oauth2.OAuth2.config.provider.OAuth2UserInfo;
import com.oauth2.OAuth2.domain.User;
import com.oauth2.OAuth2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2UserInfo userinfo = null;
        if(registrationId.equals("google")){
            System.out.println("구글 로그인 요청임당!");
            userinfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }else if(registrationId.equals("github")){
            userinfo = new GithubUserInfo(oAuth2User.getAttributes());
        }else if(registrationId.equals("kakao")){
            userinfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }
        Optional<User> userOptional = userRepository.findByProviderAndProviderId(userinfo.getProvider(), userinfo.getProviderId());
        User user;
        if (userOptional.isPresent()) {
            //이미 가입되어져있다면,
            user = userOptional.get();
            user = updateUser(userinfo, user);
        }else{
            //신규 가입자라면,
            user = registerUser(userinfo);
        }

        return new CustomOAuth2User(user, oAuth2User.getAttributes());
    }

    private User updateUser(OAuth2UserInfo userinfo, User user) {
        user.setEmail(userinfo.getEmail());
        return userRepository.save(user);
    }

    private User registerUser(OAuth2UserInfo userinfo) {
        User user = User.builder()
                .username("test")
                .email(userinfo.getEmail())
                .providerId(userinfo.getProviderId())
                .provider(userinfo.getProvider())
                .role("ROLE_USER")
                .build();
        return userRepository.save(user);
    }
}
