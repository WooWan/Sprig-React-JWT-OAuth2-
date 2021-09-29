package com.oauth2.OAuth2.config.provider;

import com.oauth2.OAuth2.config.provider.OAuth2UserInfo;

import java.util.Map;

public class GithubUserInfo implements OAuth2UserInfo {

    private Map<String, Object> attributes;

    public GithubUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return null;
    }

    @Override
    public String getProvider() {
        return "github";
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
